(function ($) {
    $.extend({
        file_upload: {
            image_input: function (options) {
                let defaults = {
                    id: 'image-file-input',
                    language: 'zh',
                    theme: 'fa',
                    uploadAsync: false,
                    allowedFileExtensions: ['jpg', 'png', 'gif', 'jpeg'],
                    maxFileCount: 10,
                    hideThumbnailContent: false,
                    focusCaptionOnBrowse: false,
                    showCaption: false,
                    showPreview: true,
                    showUpload: false,
                    showRemove: true,
                    dropZoneEnabled: false,
                    enableResumableUpload: false
                }
                options = $.extend(defaults, options);
                $('#' + options.id).fileinput({
                    language: options.language,                                                 // 设置语言
                    theme: options.theme,                                                       // 主题
                    uploadUrl: uploadUrl,                                                       // 上传的URL
                    uploadAsync: options.uploadAsync,                                           // 关闭异步上传，改为同步上传。(一次上传多张)
                    enableResumableUpload: options.enableResumableUpload,                       // 是否开启续传功能
                    allowedFileExtensions: options.allowedFileExtensions,                       // 接收的文件后缀
                    maxFileCount: options.maxFileCount,                                         // 最大文件数量
                    hideThumbnailContent: options.hideThumbnailContent,                         // 上传图片之后 是否隐藏缩略图
                    focusCaptionOnBrowse: options.focusCaptionOnBrowse,                         // 浏览并选择文件后是否聚焦说明文字
                    showCaption: options.showCaption,                                           // 是否显示说明文字
                    showPreview: options.showPreview,                                           // 是否显示预览
                    showUpload: options.showUpload,                                             // 是否显示上传按钮
                    showRemove: options.showRemove,                                             // 显示移除按钮
                    dropZoneEnabled: options.dropZoneEnabled,                                   // 是否显示拖拽区域
                    // browseClass: "btn btn-primary",                                          // 按钮样式

                }).on('change', function (event) {
                    console.log("change()           只要通过文件浏览按钮在文件输入控件中选择单个文件或多个文件，就会触发此事件。");
                }).on('fileselect', function (event, numFiles, label) {
                    console.log("fileselect()       通过文件浏览按钮在文件输入中选择文件后触发此事件。这与change事件稍有不同，即使文件浏览对话框被取消，它也会被触发");
                }).on("filebatchselected", function (event, files) {
                    $(this).fileinput("upload");
                }).on("fileuploaded", function (event, data) {
                    console.log();
                }).on("filebatchuploadsuccess", function (event, data, previewId, index) {
                    console.log('filebatchuploadsuccess() 同步上传返回结果处理')
                    if (data.response.code === web_status.SUCCESS) {
                        $.modal.alertSuccess('上传成功');
                    } else {
                        $(this).fileinput("clear");
                        $.modal.alertError(data.response.message);
                    }
                }).on('filedeleted', function (event, key) {
                    console.log('filedeleted()      在删除initialPreview内容集中的每个缩略图文件之后触发此事件');
                    console.log('Key = ' + key);
                }).on('filesuccessremove', function (event, id) {
                    console.log("使用缩略图删除按钮删除成功上传的缩略图后，会触发此事件。");
                    console.log(id);
                }).on('fileclear', function (event) {
                    console.log("fileclear()         当文件输入删除按钮或预览窗口关闭图标被按下以清除文件预览时触发此事件。");
                    console.log(event);
                }).on('filecleared', function (event) {
                    console.log("filecleared()        在预览中的文件被清除后触发此事件。");
                    console.log(event);
                }).on('filepredelete', function (event, key) {
                    console.log('filepredelete()     在删除initialPreview内容集中的每个缩略图文件之前触发此事件。');
                    console.log('Key = ' + key);
                }).on('filesuccessremove', function (event, id) {
                    console.log('filesuccessremove()   ')
                    console.log(id);
                });

            }

        },


    });


})(jQuery);