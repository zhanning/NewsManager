/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    // 编辑器样式，有三种：'kama'（默认）、'office2003'、'v2'

    config.language =  "zh-cn" ;
    config.skin = 'kama';
    config.width = 1000;
    config.height = 500;
    config.filebrowserBrowseUrl =  '/NewsProFront/static/ckfinder/ckfinder.html' ;
    config.filebrowserImageBrowseUrl =  '/NewsProFront/static/ckfinder/ckfinder.html?type=Images' ;
    config.filebrowserFlashBrowseUrl =  '/NewsProFront/static/ckfinder/ckfinder.html?type=Flash' ;
    config.filebrowserUploadUrl =  '/NewsProFront/static/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files' ;
    config.filebrowserImageUploadUrl =  '/NewsProFront/static/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images' ;
    config.filebrowserFlashUploadUrl =  '/NewsProFront/static/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash' ;
    config.filebrowserWindowWidth = '1000';   
    config.filebrowserWindowHeight = '700';
    config.removeDialogTabs = 'link:upload;image:Upload;flash:Upload;';

    config.extraPlugins += (config.extraPlugins ? ',autoformat' : 'autoformat');
	config.pasteFromWordIgnoreFontFace = false; 
	config.pasteFromWordRemoveFontStyles = false;
	config.pasteFromWordRemoveStyles = false;

};
