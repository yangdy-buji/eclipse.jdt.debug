/*
 * (c) Copyright IBM Corp. 2000, 2001, 2002.
 * All Rights Reserved.
 */
package org.eclipse.jdt.internal.debug.eval.ast.instructions;

import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.debug.core.IJavaObject;
import org.eclipse.jdt.debug.core.IJavaPrimitiveValue;
import org.eclipse.jdt.debug.core.IJavaValue;
import org.eclipse.jdt.internal.debug.core.JDIDebugPlugin;

public class Cast extends CompoundInstruction {

	public static final String IS_INSTANCE= "isInstance"; //$NON-NLS-1$
	public static final String IS_INSTANCE_SIGNATURE= "(Ljava/lang/Object;)Z"; //$NON-NLS-1$

	private int fTypeTypeId;
	
	private String fBaseTypeName;
	
	private int fDimension;
	
	/**
	 * Cast intruction constructor.
	 * 
	 * @param typeTypeId the id of the type to cast into.
	 * @param baseTypeName the base type name of the type (the type name if the
	 * type is not an array type.
	 * @param dimension the dimension of the array type, 0 if the type is not an
	 * array type.
	 */
	public Cast(int typeTypeId, String baseTypeName, int dimension, int start) {
		super(start);
		fTypeTypeId= typeTypeId;
		fBaseTypeName= baseTypeName;
		fDimension= dimension;
	}

	/*
	 * @see Instruction#execute()
	 */
	public void execute() throws CoreException {
		IJavaValue value= popValue();
		
		if (value instanceof IJavaPrimitiveValue) {
			IJavaPrimitiveValue primitiveValue = (IJavaPrimitiveValue) value;
			switch (fTypeTypeId) {
					case T_double:
						push(newValue(primitiveValue.getDoubleValue()));
						break;
					case T_float:
						push(newValue(primitiveValue.getFloatValue()));
						break;
					case T_long:
						push(newValue(primitiveValue.getLongValue()));
						break;
					case T_int:
						push(newValue(primitiveValue.getIntValue()));
						break;
					case T_short:
						push(newValue(primitiveValue.getShortValue()));
						break;
					case T_byte:
						push(newValue(primitiveValue.getByteValue()));
						break;
					case T_char:
						push(newValue(primitiveValue.getCharValue()));
						break;
			}
			
		} else {
			IJavaObject classObject;
			if (fDimension == 0) {
				classObject= getClassObject(getType(fBaseTypeName));
			} else {
				classObject= getClassObject(getArrayType(Signature.createTypeSignature(fBaseTypeName, true), fDimension));
			}
			if (classObject == null) {
				throw new CoreException(new Status(Status.ERROR, JDIDebugPlugin.getUniqueIdentifier(), Status.OK, MessageFormat.format(InstructionsEvaluationMessages.getString("Cast.No_class_object"), new String[]{typeName()}), null)); //$NON-NLS-1$
			}
			IJavaPrimitiveValue resultValue = (IJavaPrimitiveValue)classObject.sendMessage(IS_INSTANCE, IS_INSTANCE_SIGNATURE, new IJavaValue[] {value}, getContext().getThread(), false);
			if (!resultValue.getBooleanValue()) {
				throw new CoreException(new Status(Status.ERROR, JDIDebugPlugin.getUniqueIdentifier(), Status.OK, MessageFormat.format(InstructionsEvaluationMessages.getString("Cast.ClassCastException__Cannot_cast_{0}_as_{1}__1"), new String[]{value.toString(), typeName()}), null)); //$NON-NLS-1$
			}
			
			push(value);
		}
	}
	
	private String typeName() {
		String result= fBaseTypeName;
		for (int i= 0; i < fDimension; i++) {
			result+= "[]"; //$NON-NLS-1$
		}
		return result;
	}

	/*
	 * @see Object#toString()
	 */
	public String toString() {
		return InstructionsEvaluationMessages.getString("Cast.cast_3"); //$NON-NLS-1$
	}

}
