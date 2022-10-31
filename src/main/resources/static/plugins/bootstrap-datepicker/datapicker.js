$(function () {
    if ($(".datepicker").length > 0) {


        $(".datepicker").each(function (index, item) {

            var time = $(item);

            // 控制回显格式
            var format = time.attr("data-format") || 'yyyy-MM-dd';

            $(this).datepicker({
                language: 'zh-CN',
                format: format,
                autoclose: true,
                clearBtn: true,
                todayBtn: 'linked'
            });


        })


        // layui.use('laydate', function () {
        //     var com = layui.laydate;
        //     $(".time-input").each(function (index, item) {
        //         var time = $(item);
        //         // 控制控件外观
        //         var type = time.attr("data-type") || 'date';
        //         // 控制回显格式
        //         var format = time.attr("data-format") || 'yyyy-MM-dd';
        //         // 控制日期控件按钮
        //         var buttons = time.attr("data-btn") || 'clear|now|confirm', newBtnArr = [];
        //         // 日期控件选择完成后回调处理
        //         var callback = time.attr("data-callback") || {};
        //         if (buttons) {
        //             if (buttons.indexOf("|") > 0) {
        //                 var btnArr = buttons.split("|"), btnLen = btnArr.length;
        //                 for (var j = 0; j < btnLen; j++) {
        //                     if ("clear" === btnArr[j] || "now" === btnArr[j] || "confirm" === btnArr[j]) {
        //                         newBtnArr.push(btnArr[j]);
        //                     }
        //                 }
        //             } else {
        //                 if ("clear" === buttons || "now" === buttons || "confirm" === buttons) {
        //                     newBtnArr.push(buttons);
        //                 }
        //             }
        //         } else {
        //             newBtnArr = ['clear', 'now', 'confirm'];
        //         }
        //         com.render({
        //             elem: item,
        //             theme: 'molv',
        //             trigger: 'click',
        //             type: type,
        //             format: format,
        //             btns: newBtnArr,
        //             done: function (value, data) {
        //                 if (typeof window[callback] != 'undefined'
        //                     && window[callback] instanceof Function) {
        //                     window[callback](value, data);
        //                 }
        //             }
        //         });
        //     });
        // });
    }


})