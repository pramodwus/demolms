/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For complete reference see:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config

	// The toolbar groups arrangement, optimized for two toolbar rows.
/*	config.toolbarGroups = [
		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
		{ name: 'links' },
		{ name: 'insert'},
		{ name: 'forms' },
		{ name: 'tools' },
		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'others' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'styles' },
		{ name: 'colors' },
		{ name: 'about' }
	];*/
	config.toolbar = 'ToolsNoImage';
	config.toolbar_ToolsNoImage =
	[
	    { name: 'document', items : [ 'mode', 'document', 'doctools' ] },
	    { name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
	    { name: 'links', items : [ 'Link','Unlink','Anchor' ] },
	    { name: 'insert', items : [ 'base64image','Table','HorizontalRule','SpecialChar','PageBreak'] },
	    { name: 'tools', items : [ 'Preview' ] },
	    { name: 'basicstyles', items : [ 'Bold','Italic','Strike','-','RemoveFormat' ] },
	    { name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Blockquote','Align' ] },
	    '/',
	    {name: 'justify', items:[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ]},
	    { name: 'styles', items : [ 'Styles'] },
	    { name: 'tools', items : [ 'Maximize' ] },
	];
    config.toolbarCanCollapse = true;
	config.toolbarStartupExpanded = true;
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';
	config.height = '100px';
	// Simplify the dialog windows.
	    config.removeDialogTabs = 'image:advanced;link:advanced';
        config.extraPlugins = 'dialog,base64image,justify';
        CKEDITOR.config.toolbar_MA=[ ['Styles','Format','Font','FontSize'],
                                     ['Bold','Italic','Underline','StrikeThrough','-','Undo','Redo','-','Cut','Copy','Paste','Find','Replace','-','Outdent','Indent','-','Print'],
                                     ['NumberedList','BulletedList','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],[ 'Maximize' ]];
};
