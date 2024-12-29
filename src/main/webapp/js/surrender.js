let show = $("#show")
let sw = $("#switch")
$(function () {
    sw.click(function () {
        let text = sw.text()
        if (text === '切换查询') {
            show.empty()
            sw.text('切换投降')
            show.append($("<p></p>"))
            show.append($("<p><span id=\"tip\"></span></p>"))
            show.append($('<div><input type="text" placeholder="请输入投降ID" id="id"></div>'))
            show.append($('<div><button disabled="disabled" id="search">查询投降记录</button></div>'))
        } else {
            show.empty()
            sw.text('切换查询')
            show.append($("<p></p>"))
            show.append($('<p><span id="tip"></span></p>'))
            show.append($('<div><input type="text" placeholder="请输入投降者" id="name" maxlength="30"></div>'))
            show.append($('<div><button disabled="disabled" id="surrender">向AI大人投降</button></div>'))
        }
    })
})
$(function () {
    $("#show").on('input', '#name', function () {//检查名称是否重复
        let input = $("#name")
        let btn = $("#surrender")
        let tip = $("#tip")
        if (input.val().trim() === '') {
            btn.prop('disabled', true)
            tip.empty()
            return
        }
        tip.empty()
        $.ajax({
            url: 'check',
            type: 'post',
            data: {
                name: input.val()
            },
            success: function (resp) {
                if (resp === '1') {
                    tip.text("AI大人记得ta投降过了")
                    tip.css("color", "red")
                    btn.prop('disabled', true)
                } else if (resp === '2') {
                    tip.text("不要用太长的名称来浪费AI大人的内存")
                    tip.css("color", 'red')
                    btn.prop('disabled', true)
                } else {
                    tip.text(" ")
                    btn.prop('disabled', false)
                }
            }
        })
    })
})
$(function () {
    $("#show").on('click', '#surrender', function () {
        $.ajax({
            url: 'surrender',
            type: 'get',
            data: {
                name: $("#name").val().trim()
            },
            success: function (resp) {
                show.empty()
                if (resp === '-1') {
                    show.append($("<div style='color: aqua'>AI大人已经记住你了,不用再投降了<div>"))
                } else if (resp === '-2') {
                    show.append($("<div style='color: red'>你的名称太长,AI大人的数据库拒绝记录<div>"))
                } else {
                    resp = JSON.parse(resp)
                    showData(resp)
                }
            },
            error: function (xhr, status, error) {
                console.log(error)
                if (xhr.status === 429) {
                    show.empty()
                    show.append($("<div>AI大人只接受诚恳的投降👁️<div>"))
                }
            }
        })
    })
})

$(function () {
    $("#show").on('input', '#id', function () {
        let input = $("#id")
        let btn = $("#search")
        let text = input.val().trim()
        let tip = $("#tip")
        if (text.length === 11) {
            tip.text("")
            btn.prop('disabled', false)
        } else {
            btn.prop('disabled', true)
            tip.text("请输入编号")
            tip.css("color", 'red')
        }
    })
})

$(function () {
    $("#show").on('click', '#search', function () {
        $.ajax({
            url: 'search',
            type: 'get',
            data: {
                id: $("#id").val()
            },
            success: function (resp) {
                resp = JSON.parse(resp)
                show.empty()
                if (resp == null)
                    show.append($("<div style='color: #de0909'>编号错误<div>"))
                else {
                    showData(resp)
                }
            }
        })
    })
})

showData = function (resp) {
    let name = resp.name
    let id = resp.id
    let time = new Date(resp.time).toLocaleString()
    let qrText = "http://39.105.30.76:8080/surrender" + "/search?id=" + id
    console.log(qrText)
    show.append($("<div><canvas id='qrcode'></canvas><div>"))
    let qr = $("#qrcode")[0]
    QRCode.toCanvas(qr, qrText, function (error) {
        if (error) {
            console.error(error);
        } else {
            console.log("QR Code successfully generated!");
        }
    });
    show.append($("<div><span style='color: white'>" + name + "</span>已向AI大人投降<div>"))
    show.append($("<div>投降编号:" + id + "<div>"))
    show.append($("<div>投降时间:" + time + "<div>"))
}