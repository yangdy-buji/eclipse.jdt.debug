/*
 * (c) Copyright IBM Corp. 2002.
 * All Rights Reserved.
 */
package org.eclipse.jdt.debug.ui;

import org.eclipse.jdt.internal.debug.ui.JDIDebugUIPlugin;

/**
 * Constant definitions for Java debug UI plug-in.
 * <p>
 * Clients are not intended to implement this interface; constant definitions only.
 * </p>
 * @since 2.0
 */
public interface IJavaDebugUIConstants {
	/**
	 * Plug-in identifier for the Java Debug UI
	 */
	public static final String PLUGIN_ID = "org.eclipse.jdt.debug.ui"; //$NON-NLS-1$

	/**
	 * Extension point identifier for contributions of a UI page that corresponds to a VMInstallType.
	 */
	public static final String EXTENSION_POINT_VM_INSTALL_TYPE_PAGE = "vmInstallTypePage"; //$NON-NLS-1$

	/**
	 * Display view identifier (value <code>"org.eclipse.jdt.debug.ui.DisplayView"</code>).
	 */
	public static final String ID_DISPLAY_VIEW= PLUGIN_ID + ".DisplayView"; //$NON-NLS-1$
	
	/**
	 * Java snippet editor identifier (value <code>"org.eclipse.jdt.debug.ui.SnippetEditor"</code>)
	 */
	public static final String ID_JAVA_SNIPPET_EDITOR= PLUGIN_ID + ".SnippetEditor"; //$NON-NLS-1$

	/**
	 * Java snippet editor context menu identifier (value <code>"#JavaSnippetEditorContext"</code>).
	 */
	public static final String JAVA_SNIPPET_EDITOR_CONTEXT_MENU= "#JavaSnippetEditorContext"; //$NON-NLS-1$
	
	/**
	 * Java snippet editor ruler menu identifier (value <code>"#JavaSnippetRulerContext"</code>).
	 */	
	public static final String JAVA_SNIPPET_EDITOR_RULER_MENU= "#JavaSnippetRulerContext"; //$NON-NLS-1$

	/**
	 * Identifier for a group of evaluation actions in a menu (value <code>"evaluationGroup"</code>).
	 */
	public static final String EVALUATION_GROUP= "evaluationGroup"; //$NON-NLS-1$
	
	/**
	 * Status code indicating an unexpected internal error.
	 */
	public static final int INTERNAL_ERROR = 150;
}
