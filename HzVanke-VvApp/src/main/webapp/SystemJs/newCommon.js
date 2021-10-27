var _numberreg = /^[1-9]\d*$/;
var _numberreg1 = /^[0-9]\d*$/;
var _mobilereg = /^(1[3-9])[0-9]{9}$/;
var _emailreg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
var _numdecimal = /^([+]?)\d*\.?\d+$/;

Number.prototype.padLeft = function (lng, chr) {
    if (!lng) lng = 0;
    if (!chr) chr = '0';
    var vStr = this.toString();

    if (vStr.length > lng) {
        return vStr.substring(vStr.length - lng, vStr.length);
    } else if (vStr.length < lng) {
        var tnum = Math.pow(10, lng - vStr.length).toString();
        return tnum.substring(1, tnum.length).replace("0", chr) + this.toString();
    }
    return this.toString();
};

//字符串去空格
String.prototype.trim = function()
{
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
//字符串去左边空格
String.prototype.lTrim = function()
{
    return this.replace(/(^\s*)/g, "");
}
//字符串去右边空格
String.prototype.rTrim = function()
{
    return this.replace(/(\s*$)/g, "");
}

//字符串转日期
String.prototype.toDate = function () {
    var fullDate = this.split("-");
    return new Date(fullDate[0], fullDate[1] - 1, fullDate[2]);
}


//给Date原型添加转化成字符串格式yyyy-MM-dd函数
Date.prototype.toMyStr = function() {
    var result = this.getFullYear()
        + '-' + (this.getMonth() + 1).padLeft(2)
        + '-' + this.getDate().padLeft(2);
    return result;
};

//新添加的通用方法
function NewCommon() {
    //获取url参数值
    //param：参数名称
    this.getParameter = function (param) {
        var query = window.location.search;
        var iLen = param.length;
        var iStart = query.indexOf(param);
        if (iStart == -1)
            return "";
        iStart += iLen + 1;
        var iEnd = query.indexOf("&", iStart);
        if (iEnd == -1)
            return query.substring(iStart);

        return query.substring(iStart, iEnd);
    }

    this.newGuid = function () {
        var guid = "";
        for (var i = 1; i <= 32; i++) {
            var n = Math.floor(Math.random() * 16.0).toString(16);
            guid += n;
            if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
                guid += "-";
        }
        return guid;
    }

    //数组中移除一项
    this.Array_RemoveItem = function (ay, item) {
        var tmpArray = new Array();

        for (var i = 0; i < ay.length; i++) {
            if (ay[i] != item) tmpArray.push(ay[i]);
        }
        return tmpArray;
    }

    //将数组组合成字符串
    this.AyyayToString = function (ay, sp) {
        var result = "";
        for (var i = 0; i < ay.length; i++) {
            result += ay[i].replace(sp, "") + sp;
        }
        if (result != "")
            result = result.substring(0, result.length - 1);
        return result;
    }

    this.pfSelectAllTwo = function (cboxAllID, other1) {
        $("input[type='checkbox']").not("#" + cboxAllID).not("#" + other1).each(function () {
            if ($("#" + cboxAllID).prop("checked"))
                $(this).prop("checked", true);
            else
                $(this).prop("checked", false);
        });
    }

    this.pfSelectAll = function (cboxAllID) {
        $("input[type='checkbox'][disabled!='disabled']").not("#" + cboxAllID).each(function () {
            if ($("#" + cboxAllID).prop("checked"))
                $(this).prop("checked", true);
            else
                $(this).prop("checked", false);
        });
    }

    this.fSelectAll = function (boxAllID) {
        $("input[type='checkbox']").not("#" + boxAllID).each(function () {
            if ($("#" + boxAllID).prop("checked"))
                $(this).prop("checked", true);
            else
                $(this).prop("checked", false);
        });
    }

    this.pfValidDel = function (cboxAllID) {
        try {
            if ($("input[type='checkbox']:checked").not("#" + cboxAllID).length > 0)
                return true;
            else
                return false;
        }
        catch (e) {
            alert(e);
            return false;
        }
    }

    this.pfValidDelTwo = function (cboxAllID, other1) {
        if ($("input[type='checkbox']:checked").not("#" + cboxAllID).not("#" + other1).length > 0)
            return true;
        else
            return false;
    }
    this.checkPass = function (pass) {
        var _bResult = true;
        if (pass.length < 8) {
            return 0;
        }
        var ls = 0;
        if (pass.match(/([a-z])+/)) {
            ls++;
        }
        if (pass.match(/([0-9])+/)) {
            ls++;
        }
        if (pass.match(/([A-Z])+/)) {
            ls++;
        }
        if (pass.match(/[^a-zA-Z0-9]+/)) {
            ls++;
        }
        if (ls < 3) {
            _bResult = false;
        }
        return _bResult;
    }
}

var newCommon=new NewCommon();