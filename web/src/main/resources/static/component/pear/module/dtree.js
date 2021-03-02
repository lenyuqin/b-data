layui.define(["jquery", "layer", "form"], function (e) {
    var y = layui.$, f = layui.layer, r = layui.form, m = "dtree-nav-ul-sid", k = "dtree-nav-item", S = "dtree-nav-div",
        v = "dtreefont-special", o = "dtree-none-text", t = "dtree-toolbar", g = "dtree-toolbar-tool",
        l = "dtree-toolbar-fixed", D = "dtree-nav-checkbox-div", N = "d-click-checkbar", n = "t-click", c = "dtree",
        s = "dtree-nav-first-line", d = "dtree-nav-line", h = "dtree-nav-last-line", i = "dtree-nav-this",
        C = "dtree-nav-show", T = "dtree-nav-hide", x = "dtree-disabled", j = "dtree-icon-hide", a = y("body"),
        u = y(window), p = (y(document), "dtree"), b = {}, I = {}, P = "dtree-icon-fuxuankuangxuanzhong",
        w = "dtree-icon-fuxuankuang", A = "dtree-icon-fuxuankuang-banxuan", q = "dtree-icon-move-down",
        L = "dtree-icon-move-up", R = "dtree-icon-refresh", O = "dtree-icon-roundcheckfill",
        _ = "dtree-icon-roundclosefill", E = "dtree-icon-roundcheck", F = "dtree-icon-delete1",
        M = "dtree-icon-search_list_light", B = "dtree-icon-pulldown", U = "dtree-icon-pullup",
        H = "dtree-icon-roundadd", W = "dtree-icon-bianji", $ = "dtree-icon-roundclose", V = {
            "-1": {open: "dtree-icon-null-open", close: "dtree-icon-null-close"},
            0: {open: "dtree-icon-wenjianjiazhankai", close: "dtree-icon-weibiaoti5"},
            1: {open: "dtree-icon-jian", close: "dtree-icon-jia"},
            2: {open: "dtree-icon-xiangxia1", close: "dtree-icon-xiangyou"}
        }, J = {
            "-1": "dtree-icon-null",
            0: "dtree-icon-weibiaoti5",
            1: "dtree-icon-yonghu",
            2: "dtree-icon-fenzhijigou",
            3: "dtree-icon-fenguangbaobiao",
            4: "dtree-icon-xinxipilu",
            5: "dtree-icon-shuye1",
            6: "dtree-icon-caidan_xunzhang",
            7: "dtree-icon-normal-file",
            8: "dtree-icon-dian",
            9: "dtree-icon-set-sm",
            10: "dtree-icon-rate"
        }, G = "dtree-", z = "checkNodeClick", X = "itemNodeClick", K = "pulldown", Q = "pullup", Y = "addToolbar",
        Z = "editToolbar", ee = "delToolbar", te = "moveDown", oe = "moveUp", ae = "refresh", ie = "checkAll",
        ne = "unCheckAll", se = "invertAll", re = "remove", le = "searchNode", de = {
            getElemId: function (e) {
                var t = e.elem || "", o = e.obj || y(t);
                return 0 == o.length ? "" : y(o)[0].id
            }, escape: function (e) {
                return "string" != typeof e ? "" : e.replace(ue.escape, function (e) {
                    return he.escape[e]
                })
            }, unescape: function (e) {
                return "string" != typeof e ? "" : e.replace(ue.unescape, function (e) {
                    return he.unescape[e]
                })
            }, cloneObj: function (e, t) {
                var o = {};
                e instanceof Array && (o = []);
                var a = "";
                for (var i in void 0 !== t && (a = t.join(",")), e) if (-1 == a.indexOf(i)) {
                    var n = e[i];
                    o[i] = "object" == typeof n ? de.cloneObj(n, void 0 !== typeof t ? t : []) : n
                }
                return o
            }, trimToDot: function (e) {
                return e.replace(/ /g, ".")
            }
        }, ce = Object.keys || function (e) {
            e = Object(e);
            var t = [];
            for (var o in e) t.push(o);
            return t
        }, he = {escape: {"&": "&amp;", "<": "&lt;", ">": "&gt;", "'": "&quo;"}};
    he.unescape = function (e) {
        e = Object(e);
        var t = {};
        for (var o in e) t[e[o]] = o;
        return t
    }(he.escape);
    var ue = {
        escape: RegExp("[" + ce(he.escape).join("") + "]", "g"),
        unescape: RegExp("(" + ce(he.unescape).join("|") + ")", "g")
    }, fe = function (a) {
        var e = a.data ? a.data : {}, t = "boolean" != typeof a.async || a.async;
        y.ajax({
            type: a.type ? a.type : "POST",
            headers: a.headers,
            url: a.url,
            dataType: a.dataType ? a.dataType : "json",
            data: e,
            async: t,
            contentType: a.contentType,
            xhrFields: {withCredentials: a.withCredentials},
            success: a.success,
            error: function (e, t, o) {
                "function" == typeof a.error ? a.error(e, t, o) : f.msg("异步加载失败： " + t, {icon: 5, shift: 6})
            },
            statusCode: {
                404: function () {
                    f.msg("未找到指定请求，请检查访问路径！", {icon: 5, shift: 6})
                }, 500: function () {
                    f.msg("系统错误！", {icon: 5, shift: 6})
                }
            },
            complete: function (e, t) {
                "function" == typeof a.complete && a.complete(e, t)
            }
        })
    }, pe = function (e) {
        var t = "?";
        for (var o in e) t += o + "=" + e[o] + "&";
        return t = t.substring(0, t.length - 1)
    }, be = function (e) {
        this.formatter = {title: !1}, this.response = {
            statusName: "code",
            statusCode: 200,
            message: "message",
            rootName: "data",
            treeId: "id",
            parentId: "parentId",
            title: "title",
            ficonClass: "ficonClass",
            iconClass: "iconClass",
            childName: "children",
            last: "last",
            spread: "spread",
            disabled: "disabled",
            hide: "hide",
            checkArr: "checkArr",
            checked: "checked",
            type: "type",
            basicData: "basicData"
        }, this.defaultRequest = {
            nodeId: "nodeId",
            parentId: "parentId",
            context: "context",
            leaf: "leaf",
            level: "level",
            spread: "spread",
            dataType: "dataType",
            checked: "checked",
            initchecked: "initchecked",
            basicData: "basicData",
            recordData: "recordData"
        }, this.toolbarFun = {
            addTreeNode: function (e, t) {
            }, editTreeNode: function (e, t) {
            }, editTreeLoad: function (e) {
            }, delTreeNode: function (e, t) {
            }, loadToolbarBefore: function (e, t, o) {
                return e
            }
        }, this.toolbarStyle = {title: "节点", area: ["60%", "80%"]}, this.menubarFun = {
            remove: function (e) {
                return !0
            }
        }, this.menubarTips = {
            toolbar: [],
            group: [te, oe, ae, ie, ne, se, re, le],
            freedom: []
        }, this.checkbarFun = {
            chooseBefore: function (e, t) {
                return !0
            }, chooseDone: function (e) {
            }
        }, this.iframeDefaultRequest = {
            nodeId: "nodeId",
            parentId: "parentId",
            context: "context",
            leaf: "leaf",
            level: "level",
            spread: "spread",
            dataType: "dataType",
            checked: "checked",
            initchecked: "initchecked",
            basicData: "basicData",
            recordData: "recordData"
        }, this.iframeFun = {
            iframeDone: function (e) {
            }
        }, this.style = {
            item: "",
            itemThis: "",
            dfont: "",
            icon: "",
            cbox: "",
            chs: ""
        }, this.usefontStyle = {
            fnode: {node: {open: "", close: ""}, leaf: ""},
            snode: {node: {open: "", close: ""}, leaf: ""},
            checkbox: {on: "", out: "", noall: ""},
            menubar: {
                movedown: "",
                moveup: "",
                refresh: "",
                checkAll: "",
                unCheckAll: "",
                invertAll: "",
                remove: "",
                search: ""
            },
            menubarExt: "",
            toolbar: {
                menubar: {
                    movedown: "",
                    moveup: "",
                    refresh: "",
                    checkAll: "",
                    unCheckAll: "",
                    invertAll: "",
                    remove: "",
                    search: ""
                }, menubarExt: "", pulldown: "", pullup: "", add: "", edit: "", del: ""
            },
            toolbarExt: ""
        }, this.node = {
            nodeId: "",
            parentId: "",
            context: "",
            leaf: "",
            level: "",
            spread: "",
            dataType: "",
            checked: "",
            initchecked: "",
            basicData: "",
            recordData: ""
        }, this.toolbarMenu = {}, this.checkbarNode = [], this.errData = [], this.checkArrLen = 0, this.temp = [], this.bak = "", this.setting(e)
    };
    be.prototype.setting = function (e) {
        this.options = e || {}, this.elem = this.options.elem || b.elem || "", void 0 === this.options.obj ? this.elem && 0 < y(this.elem).length && (this.obj = y(this.elem)) : (this.obj = this.options.obj || b.obj || this.obj, this.elem = "#" + this.obj[0].id), this.scroll = this.options.scroll || b.scroll || this.elem, this.accordion = "boolean" == typeof this.options.accordion ? this.options.accordion : "boolean" == typeof b.accordion && b.accordion, this.accordion ? this.initLevel = 1 : this.initLevel = this.options.initLevel || b.initLevel || 2, this.type = this.options.type || b.type || "load", this.cache = "boolean" == typeof this.options.cache ? this.options.cache : "boolean" != typeof b.cache || b.cache, this.record = this.options.record || b.record || !1, this.load = "boolean" == typeof this.options.load ? this.options.load : "boolean" != typeof b.load || b.load, this.none = this.options.none || b.nont || "无数据", this.tempHeight = this.options.height || b.height, this.tempHeight ? /^full-\d+$/.test(this.tempHeight) ? (this.fullHeightGap = this.tempHeight.split("-")[1], this.height = u.height() - this.fullHeightGap) : (this.fullHeightGap = this.tempHeight, this.height = this.tempHeight) : (this.fullHeightGap = "", this.height = ""), this.width = this.options.width || b.width || "260", this.obj.css("width", this.width), this.iconfont = this.options.iconfont || b.iconfont || "dtreefont", this.iconfontStyle = this.options.iconfontStyle || b.iconfontStyle || {}, this.nodeIconArray = y.extend(V, this.options.nodeIconArray || b.nodeIconArray) || V, this.leafIconArray = y.extend(J, this.options.leafIconArray || b.leafIconArray) || J, this.skin = this.options.skin || b.skin || "theme", "layui" == this.skin ? (this.line = "boolean" == typeof this.options.line ? this.options.line : "boolean" != typeof b.line || b.line, this.ficon = this.options.ficon || b.ficon || "7", this.fnodeIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? "-1" == this.ficon ? "-1" : "1" : this.ficon[0], this.fleafIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? this.ficon : this.ficon[1], this.icon = this.options.icon || b.icon || "-1", this.nodeIcon = "string" == typeof this.icon || "number" == typeof this.icon ? (this.icon, "-1") : this.icon[0]) : "laySimple" == this.skin ? (this.line = this.options.line || b.line || !1, this.ficon = this.options.ficon || b.ficon || ["2", "-1"], this.fnodeIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? "-1" == this.ficon ? "-1" : "2" : this.ficon[0], this.fleafIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? this.ficon : this.ficon[1], this.icon = this.options.icon || b.icon || "-1", this.nodeIcon = "string" == typeof this.icon || "number" == typeof this.icon ? (this.icon, "-1") : this.icon[0]) : (this.line = this.options.line || b.line || !1, this.ficon = this.options.ficon || b.ficon || "8", this.fnodeIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? "-1" == this.ficon ? "-1" : "1" : this.ficon[0], this.fleafIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? this.ficon : this.ficon[1], this.icon = this.options.icon || b.icon || "5", this.nodeIcon = "string" == typeof this.icon || "number" == typeof this.icon ? "-1" == this.icon ? "-1" : "0" : this.icon[0]), this.leafIcon = "string" == typeof this.icon || "number" == typeof this.icon ? this.icon : this.icon[1], this.url = this.options.url || b.url || "", this.async = "boolean" == typeof this.options.async ? this.options.async : "boolean" != typeof b.async || b.async, this.headers = this.options.headers || b.headers || {}, this.method = this.options.method || b.method || "post", this.dataType = this.options.dataType || b.dataType || "json", this.contentType = this.options.contentType || b.contentType || "application/x-www-form-urlencoded", this.defaultRequest = y.extend(this.defaultRequest, this.options.defaultRequest || b.defaultRequest) || this.defaultRequest, this.filterRequest = this.options.filterRequest || b.filterRequest || [], this.request = this.options.request || b.request || {}, this.response = y.extend(this.response, this.options.response || b.response) || this.response, this.data = this.options.data || b.data || null, this.dataFormat = this.options.dataFormat || b.dataFormat || "levelRelationship", this.dataStyle = this.options.dataStyle || b.dataStyle || "defaultStyle", this.errDataShow = this.options.errDataShow || b.errDataShow || !1, this.withCredentials = this.options.withCredentials || b.withCredentials || !1, this.success = this.options.success || b.success || function (e, t) {
        }, this.done = this.options.done || b.done || function (e, t) {
        }, this.formatter = y.extend(this.formatter, this.options.formatter || b.formatter) || this.formatter, this.error = this.options.error || b.error || function (e, t, o) {
        }, this.complete = this.options.complete || b.complete || function (e, t) {
        }, this.checkbar = this.options.checkbar || b.checkbar || !1, this.checkbarLoad = this.options.checkbarLoad || b.checkbarLoad || "node", this.checkbarType = this.options.checkbarType || b.checkbarType || "all", this.checkbarData = this.options.checkbarData || b.checkbarData || "choose", this.checkbarFun = y.extend(this.checkbarFun, this.options.checkbarFun || b.checkbarFun) || this.checkbarFun, this.menubar = this.options.menubar || b.menubar || !1, this.menubarTips = y.extend(this.menubarTips, this.options.menubarTips || b.menubarTips) || this.menubarTips, this.menubarFun = y.extend(this.menubarFun, this.options.menubarFun || b.menubarFun) || this.menubarFun, this.toolbar = this.options.toolbar || b.toolbar || !1, this.toolbarWay = this.options.toolbarWay || b.toolbarWay || "contextmenu", this.toolbarStyle = y.extend(this.toolbarStyle, this.options.toolbarStyle || b.toolbarStyle) || this.toolbarStyle, this.toolbarLoad = this.options.toolbarLoad || b.toolbarLoad || "node", this.toolbarShow = this.options.toolbarShow || b.toolbarShow || ["add", "edit", "delete"], this.toolbarBtn = this.options.toolbarBtn || b.toolbarBtn || null, this.toolbarExt = this.options.toolbarExt || b.toolbarExt || [], this.toolbarFun = y.extend(this.toolbarFun, this.options.toolbarFun || b.toolbarFun) || this.toolbarFun, this.useIframe = this.options.useIframe || b.useIframe || !1, this.iframeElem = this.options.iframeElem || b.iframeElem || "", this.iframeUrl = this.options.iframeUrl || b.iframeUrl || "", this.iframeLoad = this.options.iframeLoad || b.iframeLoad || "leaf", this.iframeDefaultRequest = y.extend(this.iframeDefaultRequest, this.options.iframeDefaultRequest || b.iframeDefaultRequest) || this.iframeDefaultRequest, this.iframeRequest = y.extend(this.iframeRequest, this.options.iframeRequest) || y.extend(this.iframeRequest, b.iframeRequest) || this.iframeRequest, this.iframeFun = y.extend(this.iframeFun, this.options.iframeFun) || y.extend(this.iframeFun, b.iframeFun) || this.iframeFun, this.select = this.options.select || !1, this.select && this.selectSetting(), this.ensureTheme()
    }, be.prototype.reloadSetting = function (e) {
        this.options = y.extend(this.options, e) || this.options, this.elem = this.options.elem || this.elem, void 0 === this.options.obj ? this.elem && 0 < y(this.elem).length && (this.obj = y(this.elem)) : (this.obj = this.options.obj || this.obj, this.elem = "#" + this.obj[0].id), this.scroll = this.options.scroll || this.scroll, this.accordion = "boolean" == typeof this.options.accordion ? this.options.accordion : this.accordion, this.accordion ? this.initLevel = 1 : this.initLevel = this.options.initLevel || this.initLevel, this.type = this.options.type || this.type, this.cache = "boolean" == typeof this.options.cache ? this.options.cache : this.cache, this.record = "boolean" == typeof this.options.record ? this.options.record : this.record, this.load = "boolean" == typeof this.options.load ? this.options.load : this.load, this.none = this.options.none || this.none, this.tempHeight = this.options.height || this.height, this.tempHeight && (/^full-\d+$/.test(this.tempHeight) ? (this.fullHeightGap = this.tempHeight.split("-")[1], this.height = u.height() - this.fullHeightGap) : (this.fullHeightGap = this.tempHeight, this.height = this.tempHeight)), this.width = this.options.width || this.width, this.obj.css("width", this.width), this.line = "boolean" == typeof this.options.line ? this.options.line : this.line, this.iconfont = this.options.iconfont || this.iconfont, this.iconfontStyle = this.options.iconfontStyle || this.iconfontStyle, this.nodeIconArray = y.extend(V, this.options.nodeIconArray) || this.nodeIconArray, this.leafIconArray = y.extend(J, this.options.leafIconArray) || this.leafIconArray, this.skin = this.options.skin || this.skin, "layui" == this.skin ? (this.line = "boolean" != typeof this.options.line || this.options.line, this.ficon = this.options.ficon || this.ficon, this.fnodeIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? "-1" == this.ficon ? "-1" : "1" : this.ficon[0], this.fleafIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? this.ficon : this.ficon[1], this.icon = this.options.icon || this.icon, this.nodeIcon = "string" == typeof this.icon || "number" == typeof this.icon ? (this.icon, "-1") : this.icon[0]) : "laySimple" == this.skin ? (this.line = "boolean" == typeof this.options.line && this.options.line, this.ficon = this.options.ficon || this.ficon, this.fnodeIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? "-1" == this.ficon ? "-1" : "2" : this.ficon[0], this.fleafIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? this.ficon : this.ficon[1], this.icon = this.options.icon || this.icon, this.nodeIcon = "string" == typeof this.icon || "number" == typeof this.icon ? (this.icon, "-1") : this.icon[0]) : (this.line = "boolean" == typeof this.options.line && this.options.line, this.ficon = this.options.ficon || this.ficon, this.fnodeIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? "-1" == this.ficon ? "-1" : "1" : this.ficon[0], this.fleafIcon = "string" == typeof this.ficon || "number" == typeof this.ficon ? this.ficon : this.ficon[1], this.icon = this.options.icon || this.icon, this.nodeIcon = "string" == typeof this.icon || "number" == typeof this.icon ? "-1" == this.icon ? "-1" : "0" : this.icon[0]), this.leafIcon = "string" == typeof this.icon || "number" == typeof this.icon ? this.icon : this.icon[1], this.url = this.options.url || this.url, this.async = "boolean" == typeof this.options.async ? this.options.async : this.async, this.headers = this.options.headers || this.headers, this.method = this.options.method || this.method, this.dataType = this.options.dataType || this.dataType, this.contentType = this.options.contentType || this.contentType, this.defaultRequest = y.extend(this.defaultRequest, this.options.defaultRequest) || this.defaultRequest, this.filterRequest = this.options.filterRequest || this.filterRequest, this.request = this.options.request || this.request, this.response = y.extend(this.response, this.options.response) || this.response, this.data = this.options.data || this.data, this.dataFormat = this.options.dataFormat || this.dataFormat, this.dataStyle = this.options.dataStyle || this.dataStyle, this.errDataShow = "boolean" == typeof this.options.errDataShow ? this.options.errDataShow : this.errDataShow, this.withCredentials = "boolean" == typeof this.options.withCredentials ? this.options.withCredentials : this.withCredentials, this.success = this.options.success || this.success, this.done = this.options.done || this.done, this.formatter = y.extend(this.formatter, this.options.formatter) || this.formatter, this.error = this.options.error || this.error, this.complete = this.options.complete || this.complete, this.checkbar = this.options.checkbar || this.checkbar, this.checkbarLoad = this.options.checkbarLoad || this.checkbarLoad, this.checkbarType = this.options.checkbarType || this.checkbarType, this.checkbarData = this.options.checkbarData || this.checkbarData, this.checkbarFun = y.extend(this.checkbarFun, this.options.checkbarFun) || this.checkbarFun, this.menubar = this.options.menubar || this.menubar, this.menubarTips = y.extend(this.menubarTips, this.options.menubarTips) || this.menubarTips, this.menubarFun = y.extend(this.menubarFun, this.options.menubarFun) || this.menubarFun, this.toolbar = this.options.toolbar || this.toolbar, this.toolbarWay = this.options.toolbarWay || this.toolbarWay, this.toolbarStyle = y.extend(this.toolbarStyle, this.options.toolbarStyle) || this.toolbarStyle, this.toolbarLoad = this.options.toolbarLoad || this.toolbarLoad, this.toolbarShow = this.options.toolbarShow || this.toolbarShow, this.toolbarBtn = this.options.toolbarBtn || this.toolbarBtn, this.toolbarExt = this.options.toolbarExt || this.toolbarExt, this.toolbarFun = y.extend(this.toolbarFun, this.options.toolbarFun) || this.toolbarFun, this.useIframe = this.options.useIframe || this.useIframe, this.iframeElem = this.options.iframeElem || this.iframeElem, this.iframeUrl = this.options.iframeUrl || this.iframeUrl, this.iframeLoad = this.options.iframeLoad || this.iframeLoad, this.iframeDefaultRequest = y.extend(this.iframeDefaultRequest, this.options.iframeDefaultRequest) || this.iframeDefaultRequest, this.iframeRequest = y.extend(this.iframeRequest, this.options.iframeRequest) || this.iframeRequest, this.iframeFun = y.extend(this.iframeFun, this.options.iframeFun) || this.iframeFun, this.select && this.reloadSelectSetting(), this.ensureTheme()
    }, be.prototype.selectSetting = function () {
        this.select = !0, this.selectInitVal = this.obj.attr("data-value") || this.options.selectInitVal || "", this.selectTreeDiv = this.obj[0].id + "_tree_div", this.selectCardDiv = this.obj[0].id + "_select_card_div", this.selectDiv = this.obj[0].id + "_select_div", this.selectTipsName = this.obj[0].id + "_select_input", this.selectTips = this.options.selectTips || "请选择", this.selectInputName = this.options.selectInputName || {nodeId: this.obj[0].id + "_select_nodeId"}, this.renderSelectDom()
    }, be.prototype.reloadSelectSetting = function () {
        this.selectInitVal = this.obj.attr("data-value") || this.options.selectInitVal || this.selectInitVal, this.selectTips = this.options.selectTips || this.selectTips, this.selectInputName = y.extend(this.selectInputName, this.options.selectInputName) || this.selectInputName, this.reloadSelectDom()
    }, be.prototype.renderSelectDom = function () {
        var e = this, t = e.obj[0].id, o = e.selectInputName, a = [];
        for (var i in o) a.push('<input type="hidden" dtree-id="' + t + '" dtree-node="' + i + '" name="' + o[i] + '" value="" readonly>');
        var n = ['<div class="layui-unselect layui-form-select" dtree-id="' + t + '" dtree-select="' + e.selectDiv + '">', '<div class="layui-select-title">', a.join(""), '<input type="text" dtree-id="' + t + '" id="' + e.selectTipsName + '_id" name="' + e.selectTipsName + '" placeholder="' + e.selectTips + '" value="" readonly class="layui-input layui-unselect">', '<i class="layui-edge"></i>', "</div></div>"].join("");
        e.obj.before(n), e.obj.wrap('<div class="layui-card dtree-select" dtree-id="' + t + '" dtree-card="' + e.selectCardDiv + '"></div>').wrap('<div class="layui-card-body"></div>').wrap('<div id="' + e.selectTreeDiv + '"></div>')
    }, be.prototype.reloadSelectDom = function () {
        var e = this, t = e.obj[0].id, o = e.selectInputName, a = [];
        for (var i in o) a.push('<input type="hidden" dtree-id="' + t + '" dtree-node="' + i + '" name="' + o[i] + '" value="" readonly>');
        y("div[dtree-id='" + t + "'][dtree-select='" + e.selectDiv + "']").find("div.layui-select-title").html("");
        var n = [a.join(""), '<input type="text" dtree-id="' + t + '" id="' + e.selectTipsName + '_id" name="' + e.selectTipsName + '" placeholder="' + e.selectTips + '" value="" readonly class="layui-input layui-unselect">', '<i class="layui-edge"></i>'].join("");
        y("div[dtree-id='" + t + "'][dtree-select='" + e.selectDiv + "']").find("div.layui-select-title").html(n)
    }, be.prototype.selectVal = function (e) {
        var t = this, o = t.obj[0].id, a = t.selectInputName, i = "", n = {};
        if (t.checkbar) y("div[dtree-select='" + t.selectDiv + "']").find("input[dtree-id='" + o + "']").each(function () {
            var e = y(this).attr("name"), t = y(this).val();
            n[e] = t
        }); else {
            for (var s in void 0 === e && (e = t.getNowParam()), "string" == typeof e && (e = t.getParam(e)), i = e.context, a) n[a[s]] = e[s], y("div[dtree-select='" + t.selectDiv + "']").find("input[dtree-id='" + o + "'][name='" + a[s] + "']").val(e[s] || "");
            e.nodeId && !e.context && (i = t.getParam(e.nodeId)), y("div[dtree-select='" + t.selectDiv + "']").find("input[dtree-id='" + o + "'][name='" + t.selectTipsName + "']").val(i || "")
        }
        return n
    }, be.prototype.selectCheckboxVal = function () {
        var e = this, t = e.obj[0].id, o = e.selectInputName, a = e.getCheckbarJsonArrParam();
        selectTipsNameValue = a.context;
        var i = {};
        for (var n in o) {
            var s = a[n].join(",");
            i[o[n]] = s, y("div[dtree-select='" + e.selectDiv + "']").find("input[dtree-id='" + t + "'][name='" + o[n] + "']").val(s)
        }
        return y("div[dtree-select='" + e.selectDiv + "']").find("input[dtree-id='" + t + "'][name='" + e.selectTipsName + "']").val(selectTipsNameValue), i
    }, be.prototype.selectResetVal = function () {
        var e = this.obj[0].id;
        y("input[dtree-id='" + e + "']").val(""), this.cancelNavThis(), this.checkbar && this.cancelCheckedNode()
    }, be.prototype.ensureTheme = function () {
        this.style.item = G + this.skin + "-item", this.style.itemThis = G + this.skin + "-item-this", this.style.dfont = G + this.skin + "-dtreefont", this.style.ficon = G + this.skin + "-ficon", this.style.icon = G + this.skin + "-icon", this.style.cbox = G + this.skin + "-checkbox", this.style.chs = G + this.skin + "-choose";
        var e = this.iconfont, t = [];
        "string" == typeof e ? t.push(e) : t = e;
        var o = this.iconfontStyle, a = [];
        null == o.length ? a.push(o) : a = o;
        for (var i = 0; i < t.length; i++) {
            var n = t[i], s = a[i];
            void 0 !== s && (this.useDefaultOrUserDefineFnodeStyle(n, s.fnode), this.useDefaultOrUserDefineSnodeStyle(n, s.snode), this.useDefaultOrUserDefineCheckboxStyle(n, s.checkbox), this.useDefaultOrUserDefineMenubarStyle(n, s.menubar), this.useDefaultOrUserDefineMenubarExtStyle(n, s.menubarExt), this.useDefaultOrUserDefineToolbarStyle(n, s.toolbar), this.useDefaultOrUserDefineToolbarExtStyle(n, s.toolbarExt))
        }
    }, be.prototype.useDefaultOrUserDefineFnodeStyle = function (e, t) {
        var o = this.usefontStyle.fnode.node.open, a = this.usefontStyle.fnode.node.close,
            i = this.usefontStyle.fnode.leaf;
        if (void 0 === t) this.usefontStyle.fnode.node.open = "" == o ? e + " " + this.nodeIconArray[this.fnodeIcon].open : o, this.usefontStyle.fnode.node.close = "" == a ? e + " " + this.nodeIconArray[this.fnodeIcon].close : a, this.usefontStyle.fnode.leaf = "" == i ? e + " " + this.leafIconArray[this.fleafIcon] : i; else {
            var n = t.node, s = t.leaf;
            if (void 0 === n) this.usefontStyle.fnode.node.open = "" == o ? e + " " + this.nodeIconArray[this.fnodeIcon].open : o, this.usefontStyle.fnode.node.close = "" == a ? e + " " + this.nodeIconArray[this.fnodeIcon].close : a; else {
                var r = n.open, l = n.close;
                this.usefontStyle.fnode.node.open = void 0 === r ? "" == o ? e + " " + this.nodeIconArray[this.fnodeIcon].open : o : e + " " + r, this.usefontStyle.fnode.node.close = void 0 === l ? "" == a ? e + " " + this.nodeIconArray[this.fnodeIcon].close : a : e + " " + l
            }
            this.usefontStyle.fnode.leaf = void 0 === s ? "" == i ? e + " " + this.leafIconArray[this.fleafIcon] : i : e + " " + s
        }
    }, be.prototype.useDefaultOrUserDefineSnodeStyle = function (e, t) {
        var o = this.usefontStyle.snode.node.open, a = this.usefontStyle.snode.node.close,
            i = this.usefontStyle.snode.leaf;
        if (void 0 === t) this.usefontStyle.snode.node.open = "" == o ? e + " " + this.nodeIconArray[this.nodeIcon].open : o, this.usefontStyle.snode.node.close = "" == a ? e + " " + this.nodeIconArray[this.nodeIcon].close : a, this.usefontStyle.snode.leaf = "" == i ? e + " " + this.leafIconArray[this.leafIcon] : i; else {
            var n = t.node, s = t.leaf;
            if (void 0 === n) this.usefontStyle.snode.node.open = "" == o ? e + " " + this.nodeIconArray[this.nodeIcon].open : o, this.usefontStyle.snode.node.close = "" == a ? e + " " + this.nodeIconArray[this.nodeIcon].close : a; else {
                var r = n.open, l = n.close;
                this.usefontStyle.snode.node.open = void 0 === r ? "" == o ? e + " " + this.nodeIconArray[this.nodeIcon].open : o : e + " " + r, this.usefontStyle.snode.node.close = void 0 === l ? "" == a ? e + " " + this.nodeIconArray[this.nodeIcon].close : a : e + " " + l
            }
            this.usefontStyle.snode.leaf = void 0 === s ? "" == i ? e + " " + this.leafIconArray[this.leafIcon] : i : e + " " + s
        }
    }, be.prototype.useDefaultOrUserDefineCheckboxStyle = function (e, t) {
        var o = this.usefontStyle.checkbox.on, a = this.usefontStyle.checkbox.out, i = this.usefontStyle.checkbox.noall;
        if (void 0 === t) this.usefontStyle.checkbox.on = "" == o ? e + " " + P : o, this.usefontStyle.checkbox.out = "" == a ? e + " " + w : a, this.usefontStyle.checkbox.noall = "" == i ? e + " " + A : i; else {
            var n = t.on, s = t.out, r = t.noall;
            this.usefontStyle.checkbox.on = void 0 === n ? "" == o ? e + " " + P : o : e + " " + n, this.usefontStyle.checkbox.out = void 0 === s ? "" == a ? e + " " + w : a : e + " " + s, this.usefontStyle.checkbox.noall = void 0 === r ? "" == i ? e + " " + A : i : e + " " + r
        }
    }, be.prototype.useDefaultOrUserDefineMenubarStyle = function (e, t) {
        var o = this.usefontStyle.menubar.movedown, a = this.usefontStyle.menubar.moveup,
            i = this.usefontStyle.menubar.refresh, n = this.usefontStyle.menubar.checkAll,
            s = this.usefontStyle.menubar.unCheckAll, r = this.usefontStyle.menubar.invertAll,
            l = this.usefontStyle.menubar.remove, d = this.usefontStyle.menubar.search;
        if (void 0 === t) this.usefontStyle.menubar.movedown = "" == o ? e + " " + q : o, this.usefontStyle.menubar.moveup = "" == a ? e + " " + L : a, this.usefontStyle.menubar.refresh = "" == i ? e + " " + R : i, this.usefontStyle.menubar.checkAll = "" == n ? e + " " + O : n, this.usefontStyle.menubar.unCheckAll = "" == s ? e + " " + _ : s, this.usefontStyle.menubar.invertAll = "" == r ? e + " " + E : r, this.usefontStyle.menubar.remove = "" == l ? e + " " + F : l, this.usefontStyle.menubar.search = "" == d ? e + " " + M : d; else {
            var c = t.movedown, h = t.moveup, u = t.refresh, f = t.checkAll, p = t.unCheckAll, b = t.invertAll,
                m = t.remove, y = t.search;
            this.usefontStyle.menubar.movedown = void 0 === c ? "" == o ? e + " " + q : o : e + " " + c, this.usefontStyle.menubar.moveup = void 0 === h ? "" == a ? e + " " + L : a : e + " " + h, this.usefontStyle.menubar.refresh = void 0 === u ? "" == i ? e + " " + R : i : e + " " + u, this.usefontStyle.menubar.checkAll = void 0 === f ? "" == n ? e + " " + O : n : e + " " + f, this.usefontStyle.menubar.unCheckAll = void 0 === p ? "" == s ? e + " " + _ : s : e + " " + p, this.usefontStyle.menubar.invertAll = void 0 === b ? "" == r ? e + " " + E : r : e + " " + b, this.usefontStyle.menubar.remove = void 0 === m ? "" == l ? e + " " + F : l : e + " " + m, this.usefontStyle.menubar.search = void 0 === y ? "" == d ? e + " " + M : d : e + " " + y
        }
    }, be.prototype.useDefaultOrUserDefineMenubarExtStyle = function (e, t) {
        var o = this.usefontStyle.menubarExt;
        this.usefontStyle.menubarExt = void 0 === t ? "" == o ? e : o : t
    }, be.prototype.useDefaultOrUserDefineToolbarStyle = function (e, t) {
        var o = this.usefontStyle.toolbar.menubar.movedown, a = this.usefontStyle.toolbar.menubar.moveup,
            i = this.usefontStyle.toolbar.menubar.refresh, n = this.usefontStyle.toolbar.menubar.checkAll,
            s = this.usefontStyle.toolbar.menubar.unCheckAll, r = this.usefontStyle.toolbar.menubar.invertAll,
            l = this.usefontStyle.toolbar.menubar.remove, d = this.usefontStyle.toolbar.menubar.search,
            c = this.usefontStyle.toolbar.menubarExt, h = this.usefontStyle.toolbar.pulldown,
            u = this.usefontStyle.toolbar.pullup, f = this.usefontStyle.toolbar.add, p = this.usefontStyle.toolbar.edit,
            b = this.usefontStyle.toolbar.del;
        if (void 0 === t) this.usefontStyle.toolbar.menubar.movedown = "" == o ? e + " " + this.usefontStyle.menubar.movedown : o, this.usefontStyle.toolbar.menubar.moveup = "" == a ? e + " " + this.usefontStyle.menubar.moveup : a, this.usefontStyle.toolbar.menubar.refresh = "" == i ? e + " " + this.usefontStyle.menubar.refresh : i, this.usefontStyle.toolbar.menubar.checkAll = "" == n ? e + " " + this.usefontStyle.menubar.checkAll : n, this.usefontStyle.toolbar.menubar.unCheckAll = "" == s ? e + " " + this.usefontStyle.menubar.unCheckAll : s, this.usefontStyle.toolbar.menubar.invertAll = "" == r ? e + " " + this.usefontStyle.menubar.invertAll : r, this.usefontStyle.toolbar.menubar.remove = "" == l ? e + " " + this.usefontStyle.menubar.remove : l, this.usefontStyle.toolbar.menubar.search = "" == d ? e + " " + this.usefontStyle.menubar.search : d, this.usefontStyle.toolbar.menubarExt = "" == c ? this.usefontStyle.menubarExt : c, this.usefontStyle.toolbar.pulldown = "" == h ? e + " " + B : h, this.usefontStyle.toolbar.pullup = "" == u ? e + " " + U : u, this.usefontStyle.toolbar.add = "" == f ? e + " " + H : f, this.usefontStyle.toolbar.edit = "" == p ? e + " " + W : p, this.usefontStyle.toolbar.del = "" == b ? e + " " + $ : b; else {
            var m = t.menubar, y = t.menubarExt, v = t.pulldown, g = t.pullup, k = t.add, S = t.edit, D = t.del;
            if (void 0 === m) this.usefontStyle.toolbar.menubar.movedown = "" == o ? e + " " + this.usefontStyle.menubar.movedown : o, this.usefontStyle.toolbar.menubar.moveup = "" == a ? e + " " + this.usefontStyle.menubar.moveup : a, this.usefontStyle.toolbar.menubar.refresh = "" == i ? e + " " + this.usefontStyle.menubar.refresh : i, this.usefontStyle.toolbar.menubar.checkAll = "" == n ? e + " " + this.usefontStyle.menubar.checkAll : n, this.usefontStyle.toolbar.menubar.unCheckAll = "" == tempUncheckAll ? e + " " + this.usefontStyle.menubar.unCheckAll : tempUncheckAll, this.usefontStyle.toolbar.menubar.invertAll = "" == r ? e + " " + this.usefontStyle.menubar.invertAll : r, this.usefontStyle.toolbar.menubar.remove = "" == l ? e + " " + this.usefontStyle.menubar.remove : l, this.usefontStyle.toolbar.menubar.search = "" == d ? e + " " + this.usefontStyle.menubar.search : d; else {
                var N = m.movedown, C = m.moveup, T = m.refresh, x = m.checkAll, j = m.unCheckAll, I = m.invertAll,
                    P = m.remove, w = m.search;
                this.usefontStyle.toolbar.menubar.movedown = void 0 === N ? "" == o ? e + " " + this.usefontStyle.menubar.movedown : o : e + " " + N, this.usefontStyle.toolbar.menubar.moveup = void 0 === C ? "" == a ? e + " " + this.usefontStyle.menubar.moveup : a : e + " " + C, this.usefontStyle.toolbar.menubar.refresh = void 0 === T ? "" == i ? e + " " + this.usefontStyle.menubar.refresh : i : e + " " + T, this.usefontStyle.toolbar.menubar.checkAll = void 0 === x ? "" == n ? e + " " + this.usefontStyle.menubar.checkAll : n : e + " " + x, this.usefontStyle.toolbar.menubar.unCheckAll = void 0 === j ? "" == tempUncheckAll ? e + " " + this.usefontStyle.menubar.unCheckAll : tempUncheckAll : e + " " + j, this.usefontStyle.toolbar.menubar.invertAll = void 0 === I ? "" == r ? e + " " + this.usefontStyle.menubar.invertAll : r : e + " " + I, this.usefontStyle.toolbar.menubar.remove = void 0 === P ? "" == l ? e + " " + this.usefontStyle.menubar.remove : l : e + " " + P, this.usefontStyle.toolbar.menubar.search = void 0 === w ? "" == d ? e + " " + this.usefontStyle.menubar.search : d : e + " " + w
            }
            this.usefontStyle.toolbar.menubarExt = void 0 === y ? "" == c ? this.usefontStyle.menubarExt : c : y, this.usefontStyle.toolbar.pulldown = void 0 === v ? "" == h ? e + " " + B : h : e + " " + v, this.usefontStyle.toolbar.pullup = void 0 === g ? "" == u ? e + " " + U : u : e + " " + g, this.usefontStyle.toolbar.add = void 0 === k ? "" == f ? e + " " + H : f : e + " " + k, this.usefontStyle.toolbar.edit = void 0 === S ? "" == p ? e + " " + W : p : e + " " + S, this.usefontStyle.toolbar.del = void 0 === D ? "" == b ? e + " " + $ : b : e + " " + D
        }
    }, be.prototype.useDefaultOrUserDefineToolbarExtStyle = function (e, t) {
        var o = this.usefontStyle.toolbarExt;
        this.usefontStyle.toolbarExt = void 0 === t ? "" == o ? e : o : t
    }, be.prototype.operateIcon = function (e, t) {
        var o = this, a = e.attr("data-iconClass"), i = t.attr("data-iconClass");
        return {
            open: function () {
                e.attr("data-spread", "open"), t.attr("data-spread", "open"), a || (e.removeClass(o.usefontStyle.fnode.node.close), e.addClass(o.usefontStyle.fnode.node.open)), i || (t.removeClass(o.usefontStyle.snode.node.close), t.addClass(o.usefontStyle.snode.node.open))
            }, close: function () {
                e.attr("data-spread", "close"), t.attr("data-spread", "close"), a || (e.removeClass(o.usefontStyle.fnode.node.open), e.addClass(o.usefontStyle.fnode.node.close)), i || (t.removeClass(o.usefontStyle.snode.node.open), t.addClass(o.usefontStyle.snode.node.close))
            }, openWithLeaf: function () {
                e.attr("data-spread", "open"), t.attr("data-spread", "open"), a || (e.removeClass(o.usefontStyle.fnode.leaf), e.addClass(o.usefontStyle.fnode.node.open)), i || (t.removeClass(o.usefontStyle.snode.leaf), t.addClass(o.usefontStyle.snode.node.open))
            }, closeWithLeaf: function () {
                e.attr("data-spread", "last"), t.attr("data-spread", "last"), a || (e.removeClass(o.usefontStyle.fnode.node.open), e.removeClass(o.usefontStyle.fnode.node.close), e.addClass(o.usefontStyle.fnode.leaf)), i || (t.removeClass(o.usefontStyle.snode.node.open), t.removeClass(o.usefontStyle.snode.node.close), t.addClass(o.usefontStyle.snode.leaf))
            }
        }
    }, be.prototype.showLine = function (e) {
        var t = this;
        t.line && (e && 0 < e.length ? e.each(function () {
            t.showLineLi(y(this))
        }) : t.obj.find("li[data-id]").each(function () {
            t.showLineLi(y(this))
        }))
    }, be.prototype.showLineLi = function (e) {
        var t = e.children("div"), o = e.next("li"), a = e.parent("ul");
        if (a[0].id == this.obj[0].id) e.removeClass(d), e.removeClass(h), e.addClass(s); else {
            var i = a.parent("li").next("li");
            if (0 == i.length) 0 == o.length ? (e.removeClass(d), e.removeClass(s), e.addClass(h)) : (e.removeClass(s), e.removeClass(h), e.addClass(d)); else {
                var n = i.children("div");
                0 == o.length && "leaf" == t.children("cite").attr("data-leaf") && "leaf" == n.children("cite").attr("data-leaf") ? (e.removeClass(s), e.removeClass(d), e.addClass(h)) : (e.removeClass(s), e.removeClass(h), e.addClass(d))
            }
        }
    }, be.prototype.autoHeight = function () {
        var e = this, t = e.height;
        "" != t && (e.elem == e.scroll ? e.obj.parent().css("height", t + "px") : e.obj.closest(e.scroll).css("height", t + "px"))
    }, be.prototype.reload = function (e) {
        this.reloadSetting(e), this.init()
    }, be.prototype.init = function () {
        var n = this;
        if ("object" == typeof n) if (n.autoHeight(), n.data) {
            if (void 0 === n.data.length) return void f.msg("数据解析异常，data数据格式不正确", {icon: 5});
            if (0 == n.data.length) return void n.obj.html(n.getNoneDom().text());
            n.obj.html(""), setTimeout(function () {
                if (n.success(n.data, n.obj), "list" == n.dataFormat) {
                    var e = n.obj.attr("data-id"), t = n.queryListTreeByPid(e, n.data);
                    n.loadListTree(t, n.data, 1)
                } else n.loadTree(n.data, 1);
                n.showLine(), n.toolbar && "contextmenu" != n.toolbarWay && n.setToolbarDom().setToolbarPlace(n.toolbarMenu), n.msgErrData(), n.select && n.selectVal(n.selectInitVal), n.bak = n.obj.html(), n.done(n.data, n.obj)
            }, 100)
        } else {
            if (!n.url) return void f.msg("数据请求异常，url参数未指定", {icon: 5});
            n.obj.html("");
            var o = n.load ? f.load(1) : "";
            fe({
                async: n.async,
                headers: n.headers,
                type: n.method,
                url: n.url,
                dataType: n.dataType,
                contentType: n.contentType,
                withCredentials: n.withCredentials,
                data: n.getFilterRequestParam(n.getRequestParam()),
                success: function (e) {
                    "string" == typeof e && (e = y.parseJSON(e)), n.success(e, n.obj);
                    var t = "";
                    if ((t = "layuiStyle" == n.dataStyle ? e[n.response.statusName] : e.status[n.response.statusName]) == n.response.statusCode) {
                        var o = e[n.response.rootName];
                        if (void 0 === o.length) return void n.obj.html(n.getNoneDom().errText("数据解析异常，url回调后的数据格式不正确"));
                        if (0 == o.length) return void n.obj.html(n.getNoneDom().text());
                        if ("list" == n.dataFormat) {
                            var a = n.obj.attr("data-id"), i = n.queryListTreeByPid(a, o);
                            n.loadListTree(i, o, 1)
                        } else n.loadTree(o, 1);
                        n.showLine(), n.toolbar && "contextmenu" != n.toolbarWay && n.setToolbarDom().setToolbarPlace(n.toolbarMenu), n.msgErrData(), n.select && n.selectVal(n.selectInitVal), n.bak = n.obj.html(), n.done(e, n.obj)
                    } else "layuiStyle" == n.dataStyle ? (n.obj.html(n.getNoneDom().errText(e[n.response.message])), n.error(null, t, e[n.response.message])) : (n.obj.html(n.getNoneDom().errText(e.status[n.response.message])), n.error(null, t, e.status[n.response.message]))
                },
                error: function (e, t, o) {
                    n.obj.html(n.getNoneDom().errText(t + ": " + o)), n.error(e, t, o)
                },
                complete: function (e, t) {
                    n.load && f.close(o), n.complete(e, t)
                }
            })
        } else f.msg("树组件未成功加载，请检查配置", {icon: 5})
    }, be.prototype.getChild = function (e, t) {
        var n = this, s = e.next("ul");
        if (n.setNodeParam(e), void 0 !== t) {
            if (void 0 === t.length) return void f.msg("数据解析异常，data数据格式不正确", {icon: 5});
            if (s.html(""), "list" == n.dataFormat) {
                var o = n.node.nodeId, a = parseInt(n.node.level) + 1, i = n.queryListTreeByPid(o, t);
                n.loadListTree(i, n.data, a)
            } else n.loadTree(t, a);
            n.showLine(), n.toolbar && "contextmenu" != n.toolbarWay && n.setToolbarDom().setToolbarPlace(n.toolbarMenu), n.msgErrData(), n.bak = n.obj.html()
        } else {
            if (!n.url) return void f.msg("数据请求异常，url参数未指定", {icon: 5});
            s.html("");
            var r = n.load ? f.load(1) : "";
            fe({
                async: n.async,
                headers: n.headers,
                type: n.method,
                url: n.url,
                dataType: n.dataType,
                withCredentials: n.withCredentials,
                data: n.getFilterRequestParam(n.getRequestParam()),
                success: function (e) {
                    "string" == typeof e && (e = y.parseJSON(e));
                    var t = "";
                    if ((t = "layuiStyle" == n.dataStyle ? e[n.response.statusName] : e.status[n.response.statusName]) == n.response.statusCode) {
                        var o = n.node.nodeId, a = parseInt(n.node.level) + 1;
                        if ("list" == n.dataFormat) {
                            var i = n.queryListTreeByPid(o, e[n.response.rootName]);
                            n.loadListTree(i, e[n.response.rootName], a, s)
                        } else n.loadTree(e[n.response.rootName], a, s);
                        n.showLine(), n.toolbar && "contextmenu" != n.toolbarWay && n.setToolbarDom().setToolbarPlace(n.toolbarMenu), n.msgErrData(), s.addClass(C), n.bak = n.obj.html()
                    } else "layuiStyle" == n.dataStyle ? (n.obj.html(n.getNoneDom().errText(e[n.response.message])), n.error(null, t, e[n.response.message])) : (n.obj.html(n.getNoneDom().errText(e.status[n.response.message])), n.error(null, t, e.status[n.response.message]))
                },
                error: function (e, t, o) {
                    n.obj.html(n.getNoneDom().errText(t + ": " + o)), n.error(e, t, o)
                },
                complete: function (e, t) {
                    n.load && f.close(r), n.complete(e, t)
                }
            })
        }
    }, be.prototype.loadListTree = function (e, t, o, a) {
        var i = this;
        if (a = a || i.getNodeDom().nowOrRootUl(), 0 < e.length) for (var n = 0; n < e.length; n++) {
            var s = e[n];
            if ("object" == typeof s) {
                var r = i.parseData(s), l = i.queryListTreeByPid(r.treeId(), t);
                if (a.append(i.getLiItemDom(r.treeId(), r.parentId(), r.title(), r.fmtTitle(), r.last(l.length), r.ficonClass(), r.iconClass(), r.checkArr(), o, r.spread(o), r.disabled(), r.hide(), r.basicData(), r.recordData(), a.hasClass(c) ? "root" : "item")), 0 < l.length) {
                    var d = parseInt(o) + 1;
                    i.loadListTree(l, t, d, i.obj.find("ul[data-id='" + r.treeId() + "']"))
                }
            }
        }
    }, be.prototype.queryListTreeByPid = function (e, t) {
        var o = [];
        if (t) for (var a = 0; a < t.length; a++) {
            var i = t[a];
            "object" == typeof i && ("null" == e || null == e ? null == i[this.response.parentId] && o.push(i) : i[this.response.parentId] == e && (i[this.response.treeId] == e ? this.errData.push(i) : o.push(i)))
        }
        return o
    }, be.prototype.loadTree = function (e, t, o) {
        var a = this;
        if (e) {
            o = o || a.getNodeDom().nowOrRootUl();
            for (var i = 0; i < e.length; i++) {
                var n = e[i];
                if ("object" == typeof n) {
                    n[a.response.treeId] == n[a.response.parentId] && a.errData.push(n);
                    var s = a.parseData(n), r = s.children();
                    if (o.append(a.getLiItemDom(s.treeId(), s.parentId(), s.title(), s.fmtTitle(), s.last(r.length), s.ficonClass(), s.iconClass(), s.checkArr(), t, s.spread(t), s.disabled(), s.hide(), s.basicData(), s.recordData(), o.hasClass(c) ? "root" : "item")), 0 != r.length) {
                        var l = parseInt(t) + 1;
                        a.loadTree(r, l, a.obj.find("ul[data-id='" + s.treeId() + "']"))
                    }
                }
            }
        }
    }, be.prototype.msgErrData = function () {
        var e = this;
        if (0 < e.errData.length && e.errDataShow) {
            for (var t = "", o = 0; o < e.errData.length; o++) {
                t += "数据：【" + e.errData[o][e.response.title] + "】中节点id和上级id值一致！ \n"
            }
            f.msg(t, {icon: 2, time: 5e3})
        }
        e.errData = []
    }, be.prototype.parseData = function (o) {
        var a = this;
        return {
            treeId: function () {
                return o[a.response.treeId]
            }, parentId: function () {
                return o[a.response.parentId]
            }, fmtTitle: function () {
                if ("function" != typeof a.formatter.title) return o[a.response.title];
                var e = a.formatter.title(o), t = o[a.response.title];
                return (t = "" == e || null == e || null == e ? t : e) || ""
            }, title: function () {
                return o[a.response.title]
            }, level: function () {
                return o[a.response.level] || ""
            }, ficonClass: function () {
                return o[a.response.ficonClass] || ""
            }, iconClass: function () {
                return o[a.response.iconClass] || ""
            }, last: function (e) {
                return 0 == e ? "boolean" != typeof o[a.response.last] || o[a.response.last] : "boolean" == typeof o[a.response.last] && o[a.response.last]
            }, spread: function (e) {
                return e < a.initLevel ? "boolean" != typeof o[a.response.spread] || o[a.response.spread] : "boolean" == typeof o[a.response.spread] && o[a.response.spread]
            }, disabled: function () {
                return "boolean" == typeof o[a.response.disabled] && o[a.response.disabled]
            }, hide: function () {
                return "boolean" == typeof o[a.response.hide] && o[a.response.hide]
            }, checkArr: function () {
                var e = [], t = o[a.response.checkArr];
                return "string" == typeof t && (t = -1 < t.indexOf("{") && -1 < t.indexOf("}") ? JSON.parse(t) : {
                    type: "0",
                    checked: t
                }), "object" == typeof t && (void 0 === t.length ? e.push(t) : e = t), 0 < e.length && e.length > a.checkArrLen && (a.checkArrLen = e.length), e
            }, children: function () {
                return o[a.response.childName] || []
            }, basicData: function () {
                return de.escape(JSON.stringify(o[a.response.basicData])) || JSON.stringify({})
            }, recordData: function () {
                var e = a.record ? de.cloneObj(o, [a.response.treeId, a.response.parentId, a.response.title, a.response.iconClass, a.response.childName, a.response.last, a.response.spread, a.response.disabled, a.response.hide, a.response.checkArr, a.response.checked, a.response.type, a.response.basicData]) : {};
                return de.escape(JSON.stringify(e))
            }, data: function () {
                return o
            }
        }
    }, be.prototype.getNoneDom = function () {
        var t = this.obj[0].id, e = this.none;
        return {
            text: function () {
                return "<div class='" + o + "' dtree-id='" + t + "'>" + e + "</div>"
            }, errText: function (e) {
                return "<div class='" + o + "' dtree-id='" + t + "'>" + e + "</div>"
            }
        }
    }, be.prototype.getDom = function (r, e, t, o, l, s, d, c, a, h, u, i) {
        var f = this, p = f.obj[0].id, b = (f.toolbar, f.checkbar);
        return {
            fnode: function () {
                var e = f.fnodeIcon, t = f.fleafIcon, o = f.usefontStyle.fnode.leaf, a = f.usefontStyle.fnode.node.open,
                    i = f.usefontStyle.fnode.node.close;
                if (s) {
                    var n = f.iconfont;
                    i = "string" == typeof n ? a = o = n + " " + s : (o = n[0] + " " + s, a = n[0] + " " + s, n[0] + " " + s)
                }
                return "-1" != e && "-1" != t ? l ? "<i class='" + o + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='last' data-id='" + r + "' dtree-id='" + p + "'></i>" : h ? "<i class='" + a + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='open' data-id='" + r + "' dtree-id='" + p + "'></i>" : "<i class='" + i + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='close' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + s + "'></i>" : "-1" != e && "-1" == t ? l ? "<i class='" + o + " " + j + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='last' data-id='" + r + "' dtree-id='" + p + "'></i>" : h ? "<i class='" + a + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='open' data-id='" + r + "' dtree-id='" + p + "'></i>" : "<i class='" + i + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='close' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + s + "'></i>" : "-1" == e && "-1" != t ? l ? "<i class='" + o + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='last' data-id='" + r + "' dtree-id='" + p + "'></i>" : h ? "<i class='" + a + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='open' data-id='" + r + "' dtree-id='" + p + "'></i>" : "<i class='" + i + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='close' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + s + "'></i>" : "-1" == e && "-1" == t ? l ? "<i class='" + o + " " + j + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='last' data-id='" + r + "' dtree-id='" + p + "' style='display:none;'></i>" : h ? "<i class='" + a + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='open' data-id='" + r + "' dtree-id='" + p + "'></i>" : "<i class='" + i + " " + f.style.dfont + " " + f.style.ficon + "' data-spread='close' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + s + "'></i>" : void 0
            }, node: function () {
                var e = f.nodeIcon, t = f.leafIcon, o = f.usefontStyle.snode.leaf, a = f.usefontStyle.snode.node.open,
                    i = f.usefontStyle.snode.node.close;
                if (d) {
                    var n = f.iconfont;
                    i = "string" == typeof n ? a = o = n + " " + d : (o = n[0] + " " + d, a = n[0] + " " + d, n[0] + " " + d)
                }
                return "-1" != e && "-1" != t ? l ? "<i class='" + o + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : h ? "<i class='" + a + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : "-1" != e && "-1" == t ? l ? "<i class='" + o + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : h ? "<i class='" + a + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : "-1" == e && "-1" != t ? l ? "<i class='" + o + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : h ? "<i class='" + a + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : "-1" == e && "-1" == t ? l ? "<i class='" + o + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : h ? "<i class='" + a + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + r + "' dtree-id='" + p + "' data-iconClass='" + d + "'></i>" : void 0
            }, checkbox: function () {
                var e = !1;
                if ("node" == f.checkbarLoad ? b && (e = !0) : l && b && (e = !0), e) {
                    var t = "<div class='" + D + "' data-id='" + r + "' dtree-id='" + p + "'>";
                    if (c && 0 < c.length) for (var o = 0; o < c.length; o++) {
                        var a = c[o], i = a.checked, n = f.usefontStyle.checkbox.out;
                        n = "2" == i ? f.usefontStyle.checkbox.noall + " " + f.style.chs : "1" == i ? f.usefontStyle.checkbox.on + " " + f.style.chs : f.usefontStyle.checkbox.out;
                        var s = "";
                        u && (s = x), t += "<i class='" + n + " " + f.style.dfont + " " + f.style.cbox + " " + s + "' data-id='" + r + "' dtree-id='" + p + "' data-checked='" + a.checked + "' data-initchecked='" + a.checked + "' data-type='" + a.type + "' dtree-click='" + z + "' data-par='." + N + "' dtree-disabled='" + u + "'></i>"
                    }
                    return t += "</div>"
                }
                return ""
            }, text: function () {
                var e = "";
                return u && (e = x), "<cite class='" + n + " " + e + "' data-id='" + r + "' data-leaf='" + (l ? "leaf" : "node") + "' dtree-disabled='" + u + "' data-title='" + t + "' >" + o + "</cite>"
            }, ul: function () {
                return l ? "<ul class='" + m + "' data-id='" + r + "' dtree-id='" + p + "'></ul>" : h ? "<ul class='" + m + " " + C + "' data-id='" + r + "' dtree-id='" + p + "'></ul>" : "<ul class='" + m + "' data-id='" + r + "' dtree-id='" + p + "'></ul>"
            }
        }
    }, be.prototype.replaceDom = function (l, d, c, h, u, e) {
        var f = this, p = f.obj[0].id, b = (f.toolbar, f.checkbar);
        return {
            fnode: function (e) {
                var t = "", o = f.fnodeIcon, a = (f.fleafIcon, f.usefontStyle.fnode.leaf),
                    i = f.usefontStyle.fnode.node.open, n = f.usefontStyle.fnode.node.close;
                if (e) {
                    var s = f.iconfont;
                    n = "string" == typeof s ? i = a = s + " " + e : (a = s[0] + " " + e, i = s[0] + " " + e, s[0] + " " + e)
                }
                "-1" != o && "-1" != leafIcon ? t = c ? "<i class='" + a + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + iconClass + "'></i>" : h ? "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + iconClass + "'></i>" : "<i class='" + n + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : "-1" != nodeIcon && "-1" == leafIcon ? t = c ? "<i class='" + a + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + iconClass + "'></i>" : h ? "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + iconClass + "'></i>" : "<i class='" + n + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : "-1" == nodeIcon && "-1" != leafIcon ? t = c ? "<i class='" + a + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + iconClass + "'></i>" : h ? "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + iconClass + "'></i>" : "<i class='" + n + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : "-1" == nodeIcon && "-1" == leafIcon && (t = c ? "<i class='" + a + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + iconClass + "'></i>" : h ? "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + iconClass + "'></i>" : "<i class='" + n + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>"), "" != t && f.getNodeDom(l).fnode().replaceWith(y(t))
            }, node: function (e) {
                var t = "", o = f.nodeIcon, a = f.leafIcon, i = f.usefontStyle.snode.leaf,
                    n = f.usefontStyle.snode.node.open, s = f.usefontStyle.snode.node.close;
                if (e) {
                    var r = f.iconfont;
                    s = "string" == typeof r ? n = i = r + " " + e : (i = r[0] + " " + e, n = r[0] + " " + e, r[0] + " " + e)
                }
                "-1" != o && "-1" != a ? t = c ? "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : h ? "<i class='" + n + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : "<i class='" + s + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : "-1" != o && "-1" == a ? t = c ? "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : h ? "<i class='" + n + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : "<i class='" + s + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : "-1" == o && "-1" != a ? t = c ? "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : h ? "<i class='" + n + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : "<i class='" + s + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : "-1" == o && "-1" == a && (t = c ? "<i class='" + i + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='last' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : h ? "<i class='" + n + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='open' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>" : "<i class='" + s + " " + v + " " + f.style.dfont + " " + f.style.icon + "' data-spread='close' data-id='" + d + "' dtree-id='" + p + "' data-iconClass='" + e + "'></i>"), "" != t && f.getNodeDom(l).snode().replaceWith(y(t))
            }, checkbox: function (e) {
                var t = !1;
                if ("node" == f.checkbarLoad ? b && (t = !0) : c && b && (t = !0), t) {
                    var o = "<div class='" + D + "' data-id='" + d + "' dtree-id='" + p + "'>";
                    if (e && 0 < e.length) for (var a = 0; a < e.length; a++) {
                        var i = e[a], n = i.checked, s = f.usefontStyle.checkbox.out;
                        s = "2" == n ? f.usefontStyle.checkbox.noall + " " + f.style.chs : "1" == n ? f.usefontStyle.checkbox.on + " " + f.style.chs : f.usefontStyle.checkbox.out;
                        var r = "";
                        u && (r = x), o += "<i class='" + s + " " + f.style.dfont + " " + f.style.cbox + " " + r + "' data-id='" + d + "' dtree-id='" + p + "' data-checked='" + i.checked + "' data-initchecked='" + i.checked + "' data-type='" + i.type + "' dtree-click='" + z + "' data-par='." + N + "' dtree-disabled='" + u + "'></i>"
                    }
                    o += "</div>", f.getNodeDom(l).snode().next("div").replaceWith(y(o))
                }
            }, text: function (e) {
                var t = "";
                u && (t = x);
                var o = "<cite class='" + n + " " + t + "' data-id='" + d + "' data-leaf='" + (c ? "leaf" : "node") + "' dtree-disabled='" + u + "' >" + e + "</cite>";
                f.getNodeDom(l).cite().replaceWith(y(o))
            }, ul: function () {
                var e = c ? "<ul class='" + m + "' data-id='" + d + "' dtree-id='" + p + "'></ul>" : h ? "<ul class='" + m + " " + C + "' data-id='" + d + "' dtree-id='" + p + "'></ul>" : "<ul class='" + m + "' data-id='" + d + "' dtree-id='" + p + "'></ul>";
                f.getNodeDom(l).nextUl().replaceWith(y(e))
            }, div: function () {
                l.attr("data-id", d)
            }, basicData: function (e) {
                e = "{}" == e ? "" : e, l.attr("data-basic", e)
            }, recordData: function (e) {
                e = "{}" == e ? "" : e, l.attr("data-record", e)
            }, p_li: function (e) {
                var t = l.parent("li");
                return t.attr("data-id", d), e && t.attr("data-pid", e), t
            }
        }
    }, be.prototype.getLiItemDom = function (e, t, o, a, i, n, s, r, l, d, c, h, u, f, p) {
        var b = this, m = b.obj[0].id, y = b.getDom(e, t, o, a, i, n, s, r, l, d, c, h);
        u = "{}" == u ? "" : u, f = "{}" == f ? "" : f;
        var v = "<div class='" + S + " " + b.style.item + "' data-id='" + e + "' dtree-id='" + m + "' dtree-click='" + X + "' data-basic='" + u + "' data-record='" + f + "' dtree-disabled='" + c + "' dtree-hide='" + h + "' ";
        b.toolbar && "contextmenu" == b.toolbarWay ? ("node" == b.toolbarLoad && (v += " d-contextmenu='true'>"), "noleaf" == b.toolbarLoad && (v += i ? " d-contextmenu='false'>" : " d-contextmenu='true'>"), "leaf" == b.toolbarLoad && (v += i ? " d-contextmenu='true'>" : " d-contextmenu='false'>")) : v += " d-contextmenu='false'>";
        var g = "";
        return h && (g = T), ["<li class='" + N + " " + k + " " + g + " 'data-id='" + e + "'data-pid='" + ("root" == p ? void 0 !== typeof t && "" != t ? t : "-1" : t) + "'dtree-id='" + m + "'data-index='" + l + "'dtree-hide='" + h + "'>" + v, y.fnode(), y.node(), y.checkbox(), y.text(), "</div>", y.ul(), "</li>"].join("")
    }, be.prototype.dataInit = function (e) {
        var t = this, o = t.obj.find("div[data-id='" + e + "']");
        t.getNodeDom(o).parentLi().find("." + i).removeClass(i), t.getNodeDom(o).parentLi().find("." + t.style.itemThis).removeClass(t.style.itemThis), o.addClass(i), o.addClass(t.style.itemThis), t.setNodeParam(o);
        var a = o.parents("." + k);
        return a.children("ul").addClass(C), a.children("." + S).children("i[data-spread]." + de.trimToDot(t.usefontStyle.fnode.node.close)).addClass(t.usefontStyle.fnode.node.open), a.children("." + S).children("i[data-spread]." + de.trimToDot(t.usefontStyle.fnode.node.close)).removeClass(t.usefontStyle.fnode.node.close), a.children("." + S).children("i[data-spread]." + de.trimToDot(t.usefontStyle.snode.node.close)).addClass(t.usefontStyle.snode.node.open), a.children("." + S).children("i[data-spread]." + de.trimToDot(t.usefontStyle.snode.node.close)).removeClass(t.usefontStyle.snode.node.close), t.getNowParam()
    }, be.prototype.rollbackHtml = function (e) {
        var t = this;
        t.bak && (t.obj.html(t.bak), t.cancelNavThis(), t.checkbar ? (t.cancelCheckedNode(), t.chooseDataInit(e)) : t.dataInit(e), t.bak = t.obj.html())
    }, be.prototype.escape = function (e) {
        return de.escape(e)
    }, be.prototype.unescape = function (e) {
        return de.unescape(e)
    }, be.prototype.cancelNavThis = function () {
        this.obj.find("div[data-id]").parent().find("." + i).removeClass(i), this.obj.find("div[data-id]").parent().find("." + this.style.itemThis).removeClass(this.style.itemThis)
    }, be.prototype.navThis = function (e) {
        var t = "object" == typeof e ? e : 0 == this.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']").length ? null : this.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']");
        null != t && (this.cancelNavThis(), t.addClass(i), t.addClass(this.style.itemThis))
    }, be.prototype.accordionUL = function (e) {
        if (this.accordion) {
            e.closest("li[data-index]").siblings("li[data-index]").children("ul[data-id]").removeClass(C);
            var t = e.closest("li[data-index]").siblings("li[data-index]").children("ul[data-id]").prev("div");
            if (t.length && 0 < t.length) for (var o = 0; o < t.length; o++) {
                var a = y(t[o]), i = this.getNodeDom(a).fnode(), n = this.getNodeDom(a).snode();
                "last" != i.attr("data-spread") && this.operateIcon(i, n).close()
            }
        }
    }, be.prototype.clickSpread = function (e) {
        var t = this, o = t.getNodeDom(e).fnode(), a = t.getNodeDom(e).snode(),
            i = (t.getNodeDom(e).cite(), o.attr("data-spread")), n = e.next("ul");
        0 < n.length && ("close" == i ? ("load" == t.type ? t.cache ? n.html() ? n.addClass(C) : t.getChild(e) : (n.html(""), t.getChild(e)) : n.addClass(C), t.accordionUL(n), t.operateIcon(o, a).open()) : "open" == i && (n.removeClass(C), t.operateIcon(o, a).close()))
    }, be.prototype.setDisabledNodes = function (e) {
        for (var t = e.split(","), o = 0; o < t.length; o++) {
            var a = this.getNodeDom(t[o]).div(), i = a.children("div." + D).children("i[data-par]"),
                n = a.children("cite[data-leaf]");
            null != a && "true" != a.attr("dtree-disabled") && (a.attr("dtree-disabled", "true"), i.attr("dtree-disabled", "true"), i.addClass(x), n.attr("dtree-disabled", "true"), n.addClass(x))
        }
    }, be.prototype.setDisabledAllNodes = function () {
        this.obj.find("div[dtree-click='" + X + "']").each(function () {
            var e = y(this), t = e.children("div." + D).children("i[data-par]"), o = e.children("cite[data-leaf]");
            null != e && "true" != e.attr("dtree-disabled") && (e.attr("dtree-disabled", "true"), t.attr("dtree-disabled", "true"), t.addClass(x), o.attr("dtree-disabled", "true"), o.addClass(x))
        })
    }, be.prototype.cancelDisabledNodes = function (e) {
        for (var t = e.split(","), o = 0; o < t.length; o++) {
            var a = this.getNodeDom(t[o]).div(), i = a.children("div." + D).children("i[data-par]"),
                n = a.children("cite[data-leaf]");
            null != a && "true" == a.attr("dtree-disabled") && (a.attr("dtree-disabled", "false"), i.attr("dtree-disabled", "false"), i.removeClass(x), n.attr("dtree-disabled", "false"), n.removeClass(x))
        }
    }, be.prototype.getDisabledNodesParam = function (e) {
        for (var t = e.split(","), o = [], a = 0; a < t.length; a++) {
            var i = this.getNodeDom(t[a]).div();
            null != i && "true" == i.attr("dtree-disabled") && o.push(this.getRequestParam(this.getTempNodeParam(i)))
        }
        return o
    }, be.prototype.getAllDisabledNodesParam = function () {
        var t = this, o = [];
        return t.obj.find("div[dtree-click='" + X + "'][dtree-disabled='true']").each(function () {
            var e = y(this);
            o.push(t.getRequestParam(t.getTempNodeParam(e)))
        }), o
    }, be.prototype.setHideNodes = function (e) {
        for (var t = e.split(","), o = 0; o < t.length; o++) {
            var a = this.getNodeDom(t[o]).div(), i = a.parent("li[dtree-hide]");
            null != a && "true" != a.attr("dtree-hide") && (a.attr("dtree-hide", "true"), i.attr("dtree-hide", "true"), i.addClass(T))
        }
    }, be.prototype.cancelHideNodes = function (e) {
        for (var t = e.split(","), o = 0; o < t.length; o++) {
            var a = this.getNodeDom(t[o]).div(), i = a.parent("li[dtree-hide]");
            null != a && "true" == a.attr("dtree-hide") && (a.attr("dtree-hide", "false"), i.attr("dtree-hide", "false"), i.removeClass(T))
        }
    }, be.prototype.getHideNodesParam = function (e) {
        for (var t = e.split(","), o = [], a = 0; a < t.length; a++) {
            var i = this.getNodeDom(t[a]).div();
            null != i && "true" == i.attr("dtree-hide") && o.push(this.getRequestParam(this.getTempNodeParam(i)))
        }
        return o
    }, be.prototype.getAllHideNodesParam = function () {
        var t = this, o = [];
        return t.obj.find("div[dtree-click='" + X + "'][dtree-hide='true']").each(function () {
            var e = y(this);
            o.push(t.getRequestParam(t.getTempNodeParam(e)))
        }), o
    }, be.prototype.refreshTree = function () {
        this.obj.html(""), this.initNodeParam(), this.init()
    }, be.prototype.partialRefreshAdd = function (e, t) {
        var o = this;
        $ul = e.next("ul");
        var a = e.find("i[data-spread]");
        if ("last" == a.eq(0).attr("data-spread") ? o.operateIcon(a.eq(0), a.eq(1)).openWithLeaf() : o.operateIcon(a.eq(0), a.eq(1)).open(), $ul.addClass(C), o.accordionUL($ul), t) if (t.length && 0 < t.length) o.getChild(e, t); else {
            var i = o.parseData(t);
            if (i.treeId()) {
                var n = parseInt(e.parent("li").attr("data-index")) + 1;
                $ul.append(o.getLiItemDom(i.treeId(), i.parentId(), i.title(), i.fmtTitle(), i.last(0), i.ficonClass(), i.iconClass(), i.checkArr(), n, i.spread(), i.disabled(), i.hide(), i.basicData(), i.recordData(), "item")), $thisDiv = $ul.find("div[data-id='" + i.treeId() + "']"), o.setNodeParam($thisDiv), o.showLine($ul.find("li"))
            } else f.msg("添加失败,节点ID为undefined！", {icon: 5}), o.setNodeParam(e)
        } else o.getChild(e)
    }, be.prototype.partialRefreshEdit = function (e, t) {
        var o = this;
        if ($ul = e.next("ul"), $p_li = e.parent("li"), t) if ("object" == typeof t) {
            var a = o.parseData(t);
            if (a.treeId()) {
                var i = o.replaceDom(e, a.treeId(), a.last(0), a.spread(), a.disabled(), a.hide());
                i.div(), i.node(a.iconClass()), i.checkbox(a.checkArr()), i.text(a.title()), i.ul(), i.basicData(a.basicData()), i.recordData(a.recordData());
                var n = a.parentId(), s = $p_li.attr("data-pid");
                if (n && n != s) {
                    $p_li = i.p_li(n);
                    var r = o.getNodeDom(n).div(), l = o.getNodeDom(n).nextUl();
                    if (0 == l.children("li").length) {
                        var d = r.find("i[data-spread]");
                        o.operateIcon(d.eq(0), d.eq(1)).openWithLeaf(), l.addClass(C)
                    }
                    l.append($p_li)
                } else i.p_li();
                o.setNodeParam(e)
            } else f.msg("编辑失败,节点ID为undefined！", {icon: 5}), o.setNodeParam(e)
        } else o.getNodeDom(e).cite().html(t)
    }, be.prototype.partialRefreshDel = function (e) {
        var t = this;
        if ($p_li = e.parent("li"), $p_ul = t.getNodeDom(e).parentUl(), $p_div = t.getNodeDom(e).parentDiv(), $p_li.remove(), t.showLine($p_ul.find("li")), 0 == $p_ul.children("li").length) {
            var o = $p_div.find("i[data-spread]");
            t.operateIcon(o.eq(0), o.eq(1)).closeWithLeaf()
        }
        t.initNodeParam()
    }, be.prototype.chooseDataInit = function (e) {
        for (var t = this, o = e.split(","), a = 0; a < o.length; a++) t.obj.find("i[dtree-click='" + z + "']").each(function () {
            y(this).attr("data-id") == o[a] && t.checkStatus(y(this)).check()
        });
        var i = t.obj.find("i[dtree-click='" + z + "'][data-checked='1']").parents("." + k);
        return i.children("ul").addClass(C), i.children("." + S).children("i[data-spread]." + de.trimToDot(t.usefontStyle.fnode.node.close)).addClass(t.usefontStyle.fnode.node.open), i.children("." + S).children("i[data-spread]." + de.trimToDot(t.usefontStyle.fnode.node.close)).removeClass(t.usefontStyle.fnode.node.close), i.children("." + S).children("i[data-spread]." + de.trimToDot(t.usefontStyle.snode.node.close)).addClass(t.usefontStyle.snode.node.open), i.children("." + S).children("i[data-spread]." + de.trimToDot(t.usefontStyle.snode.node.close)).removeClass(t.usefontStyle.snode.node.close), t.getCheckbarNodesParam()
    }, be.prototype.checkAllOrNot = function (e) {
        var t = this, o = e.attr("data-par"), a = e.attr("data-type"), i = e.closest(o), n = e.parents(o),
            s = i.find(o);
        if ("1" == e.attr("data-checked")) {
            t.checkStatus(e).noCheck();
            var r = s.find(">." + S + ">." + D + ">i[data-type='" + a + "']");
            t.checkStatus(r).noCheck();
            for (var l = 1, d = n; l < d.length; l++) {
                if (0 == d.eq(l).find(">." + m + " ." + D + ">i[data-type='" + a + "'][data-checked='1']").length) {
                    var c = d.eq(l).find(">." + S + ">." + D + ">i[data-type='" + a + "']");
                    t.checkStatus(c).noCheck()
                }
            }
        } else {
            t.checkStatus(e).check();
            r = s.find(">." + S + ">." + D + ">i[data-type='" + a + "']");
            t.checkStatus(r).check();
            for (l = 1, d = n; l < d.length; l++) {
                c = d.eq(l).find(">." + S + ">." + D + ">i[data-type='" + a + "']");
                t.checkStatus(c).check()
            }
        }
    }, be.prototype.checkAllOrNoallOrNot = function (e) {
        var t = this, o = (e.closest("." + S), e.attr("data-par")), a = e.attr("data-type"), i = e.closest(o),
            n = e.parents(o), s = i.find(o);
        if ("1" == e.attr("data-checked")) {
            t.checkStatus(e).noCheck();
            var r = s.find(">." + S + ">." + D + ">i[data-type='" + a + "']");
            t.checkStatus(r).noCheck();
            for (var l = 1, d = n; l < d.length; l++) {
                var c = d.eq(l).find(">." + m + " ." + D + ">i[data-type='" + a + "'][data-checked='1']").length,
                    h = d.eq(l).find(">." + S + ">." + D + ">i[data-type='" + a + "']");
                0 == c ? t.checkStatus(h).noCheck() : t.checkStatus(h).noallCheck()
            }
        } else {
            t.checkStatus(e).check();
            r = s.find(">." + S + ">." + D + ">i[data-type='" + a + "']");
            t.checkStatus(r).check();
            for (l = 1, d = n; l < d.length; l++) {
                var u = d.eq(l).find(">." + m + " ." + D + ">i[data-type='" + a + "'][data-checked='1']").length,
                    f = d.eq(l).find(">." + m + " ." + D + ">i[data-type='" + a + "']").length;
                h = d.eq(l).find(">." + S + ">." + D + ">i[data-type='" + a + "']");
                u != f ? t.checkStatus(h).noallCheck() : t.checkStatus(h).check()
            }
        }
    }, be.prototype.checkAllOrPcascOrNot = function (e) {
        e.closest("." + S);
        var t = e.attr("data-par"), o = e.attr("data-type"), a = e.closest(t), i = (e.parents(t), a.find(t));
        if ("1" == e.attr("data-checked")) {
            this.checkStatus(e).noCheck();
            var n = i.find(">." + S + ">." + D + ">i[data-type='" + o + "']");
            this.checkStatus(n).noCheck()
        } else {
            this.checkStatus(e).check();
            n = i.find(">." + S + ">." + D + ">i[data-type='" + o + "']");
            this.checkStatus(n).check()
        }
    }, be.prototype.checkOrNot = function (e) {
        e.closest("." + S);
        var t = e.attr("data-par"), o = (e.attr("data-type"), e.closest(t));
        e.parents(t), o.find(t);
        "1" == e.attr("data-checked") ? this.checkStatus(e).noCheck() : this.checkStatus(e).check()
    }, be.prototype.checkOnly = function (e) {
        e.closest("." + S);
        var t = e.attr("data-par"), o = (e.attr("data-type"), e.closest(t)),
            a = (e.parents(t), o.find(t), e.attr("data-checked")), i = this.obj.find("i[data-checked]");
        this.checkStatus(i).noCheck(), "1" != a && this.checkStatus(e).check()
    }, be.prototype.changeCheck = function (e) {
        var t = this, o = t.temp;
        void 0 === e && (e = o[0]), "all" == t.checkbarType ? t.checkAllOrNot(e) : "no-all" == t.checkbarType ? t.checkAllOrNoallOrNot(e) : "p-casc" == t.checkbarType ? t.checkAllOrPcascOrNot(e) : "self" == t.checkbarType ? t.checkOrNot(e) : "only" == t.checkbarType ? t.checkOnly(e) : t.checkAllOrNot(e), t.select && t.selectCheckboxVal();
        var a = t.setAndGetCheckbarNodesParam(!0);
        t.checkbarFun.chooseDone(a), layui.event.call(this, p, "chooseDone(" + y(t.obj)[0].id + ")", {checkbarParams: a}), t.temp = []
    }, be.prototype.initNoAllCheck = function () {
        var e = this.obj.find("i[data-checked='1']");
        if (0 < e.length) for (var t = 0; t < e.length; t++) for (var o = y(e[t]), a = o.attr("data-par"), i = o.attr("data-type"), n = o.closest(a), s = o.parents(a), r = (n.find(a), 1), l = s; r < l.length; r++) {
            var d = l.eq(r).find(">." + m + " ." + D + ">i[data-type='" + i + "'][data-checked='1']").length,
                c = l.eq(r).find(">." + m + " ." + D + ">i[data-type='" + i + "']").length,
                h = l.eq(r).find(">." + S + ">." + D + ">i[data-type='" + i + "']");
            d != c ? this.checkStatus(h).noallCheck() : this.checkStatus(h).check()
        }
    }, be.prototype.initAllCheck = function () {
        var e = this.obj.find("i[data-checked='1']");
        if (0 < e.length) for (var t = 0; t < e.length; t++) for (var o = y(e[t]), a = o.attr("data-par"), i = o.attr("data-type"), n = o.closest(a), s = o.parents(a), r = (n.find(a), 1), l = s; r < l.length; r++) {
            var d = l.eq(r).find(">." + S + ">." + D + ">i[data-type='" + i + "']");
            this.checkStatus(d).check()
        }
    }, be.prototype.checkStatus = function (e) {
        var t = this;
        return {
            check: function () {
                e.removeClass(t.usefontStyle.checkbox.out), e.removeClass(t.usefontStyle.checkbox.noall), e.addClass(t.usefontStyle.checkbox.on), e.addClass(t.style.chs), e.attr("data-checked", "1")
            }, noCheck: function () {
                e.removeClass(t.usefontStyle.checkbox.noall), e.removeClass(t.usefontStyle.checkbox.on), e.removeClass(t.style.chs), e.addClass(t.usefontStyle.checkbox.out), e.attr("data-checked", "0")
            }, noallCheck: function () {
                e.removeClass(t.usefontStyle.checkbox.out), e.removeClass(t.usefontStyle.checkbox.on), e.addClass(t.usefontStyle.checkbox.noall), e.addClass(t.style.chs), e.attr("data-checked", "2")
            }
        }
    }, be.prototype.setAndGetCheckbarNodesParam = function (o) {
        var a = this;
        return a.checkbarNode = [], "change" == a.checkbarData ? a.obj.find("i[data-par][dtree-disabled='false']").each(function () {
            var e = y(this), t = e.closest("." + S);
            e.attr("data-checked") != e.attr("data-initchecked") && (o ? a.checkbarNode.push(a.getRequestParam(a.getCheckbarNodeParam(t, e))) : a.checkbarNode.push(a.getCheckbarNodeParam(t, e)))
        }) : "all" == a.checkbarData ? a.obj.find("i[data-par][data-checked][dtree-disabled='false']").each(function () {
            var e = y(this), t = e.closest("." + S);
            o ? a.checkbarNode.push(a.getRequestParam(a.getCheckbarNodeParam(t, e))) : a.checkbarNode.push(a.getCheckbarNodeParam(t, e))
        }) : "choose" == a.checkbarData ? a.obj.find("i[data-par][data-checked='1'][dtree-disabled='false']").each(function () {
            var e = y(this), t = e.closest("." + S);
            o ? a.checkbarNode.push(a.getRequestParam(a.getCheckbarNodeParam(t, e))) : a.checkbarNode.push(a.getCheckbarNodeParam(t, e))
        }) : "halfChoose" == a.checkbarData && (a.obj.find("i[data-par][data-checked='1'][dtree-disabled='false']").each(function () {
            var e = y(this), t = e.closest("." + S);
            o ? a.checkbarNode.push(a.getRequestParam(a.getCheckbarNodeParam(t, e))) : a.checkbarNode.push(a.getCheckbarNodeParam(t, e))
        }), a.obj.find("i[data-par][data-checked='2'][dtree-disabled='false']").each(function () {
            var e = y(this), t = e.closest("." + S);
            o ? a.checkbarNode.push(a.getRequestParam(a.getCheckbarNodeParam(t, e))) : a.checkbarNode.push(a.getCheckbarNodeParam(t, e))
        })), a.checkbarNode
    }, be.prototype.getCheckbarNodesParam = function () {
        return this.setAndGetCheckbarNodesParam(!0)
    }, be.prototype.getCheckbarNodeParam = function (e, t) {
        var o = this, a = {};
        a.nodeId = e.attr("data-id"), a.parentId = o.getNodeDom(e).parentLi().attr("data-pid"), a.context = "function" == typeof o.formatter.title ? o.getNodeDom(e).cite().attr("data-title") : o.getNodeDom(e).cite().text(), a.leaf = "leaf" == o.getNodeDom(e).cite().attr("data-leaf"), a.level = o.getNodeDom(e).parentLi().attr("data-index"), a.spread = "open" == o.getNodeDom(e).fnode().attr("data-spread");
        var i = e.attr("data-basic");
        i && (i = JSON.parse(de.unescape(i))), a.basicData = i;
        var n = e.attr("data-record");
        return n && (n = JSON.parse(de.unescape(n))), a.recordData = n, a.dataType = t.attr("data-type"), a.checked = t.attr("data-checked"), a.initchecked = t.attr("data-initchecked"), a
    }, be.prototype.getCheckbarJsonArrParam = function () {
        var e = {
            nodeId: [],
            parentId: [],
            context: [],
            leaf: [],
            level: [],
            spread: [],
            dataType: [],
            checked: [],
            initchecked: [],
            basicData: [],
            recordData: []
        }, t = this.setAndGetCheckbarNodesParam(!1);
        if (t && 0 < t.length) for (var o = 0; o < t.length; o++) {
            var a = t[o];
            console.log(a), e.nodeId.push(a.nodeId), e.parentId.push(a.parentId), e.context.push(a.context), e.leaf.push(a.leaf), e.level.push(a.level), e.spread.push(a.spread), e.dataType.push(a.dataType), e.checked.push(a.checked), e.initchecked.push(a.initchecked), e.basicData.push(a.basicData), e.recordData.push(a.recordData)
        }
        return e = this.getRequestParam(e), console.log(e), e
    }, be.prototype.changeCheckbarNodes = function () {
        var t = !1;
        return this.obj.find("i[data-par]").each(function () {
            var e = y(this);
            if ($div = e.closest("." + S), e.attr("data-checked") != e.attr("data-initchecked")) return t = !0
        }), t
    }, be.prototype.clickNodeCheckbar = function (e) {
        var t = this.getNodeDom(e).checkbox();
        this.temp = [t], this.changeCheck()
    }, be.prototype.checkAllNode = function (e) {
        var t = this.obj.find("i[data-par][data-checked!='1']");
        0 < t.length && this.checkStatus(t).check()
    }, be.prototype.cancelCheckedNode = function (e) {
        var t = this.obj.find("i[data-par][data-checked!='0']");
        0 < t.length && this.checkStatus(t).noCheck()
    }, be.prototype.invertCheckedNode = function (e) {
        var t = this;
        if (0 < t.obj.find("i[data-par]").length) {
            var o = !1;
            t.obj.find("i[data-par]").each(function () {
                var e = y(this);
                "2" == e.attr("data-checked") ? o = !0 : "0" == e.attr("data-checked") ? t.checkStatus(e).check() : "1" == e.attr("data-checked") && t.checkStatus(e).noCheck()
            }), o ? t.initNoAllCheck() : t.initAllCheck()
        }
    }, be.prototype.removeCheckedNode = function (e) {
        var o = this;
        if (0 == o.obj.find("i[data-par][data-checked='1']").length) f.msg("请至少选中一个节点", {icon: 2}); else {
            o.checkbarNode = [];
            o.obj.find("i[data-par][data-checked='1']").each(function () {
                var e = y(this), t = e.closest("." + S);
                o.checkbarNode.push(o.getRequestParam(o.getCheckbarNodeParam(t, e)))
            }), f.confirm("确定要删除选中节点？", {icon: 3, title: "删除选中节点"}, function (e) {
                o.menubarFun.remove(o.checkbarNode) && (o.obj.find("i[data-par][data-checked='1']").closest("." + S).next("ul").remove(), o.obj.find("i[data-par][data-checked='1']").closest("." + S).remove(), o.checkbarNode = []), f.close(e)
            })
        }
    }, be.prototype.initTreePlus = function () {
        var e = this;
        e.obj.prevAll("div#dtree_menubar_" + e.obj[0].id).remove(), e.toolbarMenu = {}, e.menubar && e.menubarTips.group && 0 < e.menubarTips.group.length && e.obj.before("<div class='dtree-menubar' id='dtree_menubar_" + e.obj[0].id + "'><div class='layui-btn-group'></div></div>"), e.toolbar && "contextmenu" == e.toolbarWay && (e.obj.prevAll("div#dtree_toolbar_" + e.obj[0].id).remove(), e.obj.before("<div class='" + t + " layui-nav' id='dtree_toolbar_" + e.obj[0].id + "'><div class='layui-nav-item'><dl class='layui-nav-child layui-anim'></dl></div></div>"))
    }, be.prototype.openTreePlus = function () {
        var e = this, t = [];
        if (e.toolbar && e.getToolbarDom(), e.menubar) {
            var o = e.menubarTips, a = o.toolbar, i = o.group;
            o.freedom;
            if (a && 0 < a.length) for (var n = 0; n < a.length; n++) {
                var s = a[n];
                "string" == typeof s && e.getMenubarToolDom(s), "object" == typeof s && e.getExtMenubarToolDom(s)
            }
            if (i && 0 < i.length) {
                for (n = 0; n < i.length; n++) {
                    var r = i[n];
                    "string" == typeof r && t.push(e.getMenubarDom(r)), "object" == typeof r && t.push(e.getExtMenubarDom(r))
                }
                e.obj.prevAll("div#dtree_menubar_" + e.obj[0].id).children("div.layui-btn-group").append(t.join(""))
            }
        }
    }, be.prototype.getMenubarDom = function (e) {
        var t = this, o = t.obj[0].id, a = "";
        switch (e) {
            case te:
                a = "<button type='button' class='layui-btn layui-btn-sm layui-btn-primary' dtree-id='" + o + "' d-menu='" + te + "' title='展开全部节点'><i class='" + t.usefontStyle.menubar.movedown + "'></i></button>";
                break;
            case oe:
                a = "<button type='button' class='layui-btn layui-btn-sm layui-btn-primary' dtree-id='" + o + "' d-menu='" + oe + "' title='收缩全部节点'><i class='" + t.usefontStyle.menubar.moveup + "'></i></button>";
                break;
            case ae:
                a = "<button type='button' class='layui-btn layui-btn-sm layui-btn-primary' dtree-id='" + o + "' d-menu='" + ae + "' title='刷新'><i class='" + t.usefontStyle.menubar.refresh + "'></i></button>";
                break;
            case ie:
                a = t.checkbar && "only" != t.checkbarType ? "<button type='button' class='layui-btn layui-btn-sm layui-btn-primary' dtree-id='" + o + "' d-menu='" + ie + "' title='全选节点'><i class='" + t.usefontStyle.menubar.checkAll + "'></i></button>" : "";
                break;
            case ne:
                a = t.checkbar && "only" != t.checkbarType ? "<button type='button' class='layui-btn layui-btn-sm layui-btn-primary' dtree-id='" + o + "' d-menu='" + ne + "' title='全不选节点'><i class='" + t.usefontStyle.menubar.unCheckAll + "'></i></button>" : "";
                break;
            case se:
                a = t.checkbar && "only" != t.checkbarType ? "<button type='button' class='layui-btn layui-btn-sm layui-btn-primary' dtree-id='" + o + "' d-menu='" + se + "' title='反选节点'><i class='" + t.usefontStyle.menubar.invertAll + "'></i></button>" : "";
                break;
            case re:
                a = t.checkbar ? "<button type='button' class='layui-btn layui-btn-sm layui-btn-primary' dtree-id='" + o + "' d-menu='" + re + "' title='删除选中节点'><i class='" + t.usefontStyle.menubar.remove + "'></i></button>" : "";
                break;
            case le:
                a = "<button type='button' class='layui-btn layui-btn-sm layui-btn-primary' dtree-id='" + o + "' d-menu='" + le + "' title='查询节点'><i class='" + t.usefontStyle.menubar.search + "'></i></button>"
        }
        return a
    }, be.prototype.getExtMenubarDom = function (e) {
        return "<button type='button' class='layui-btn layui-btn-sm layui-btn-primary' dtree-id='" + this.obj[0].id + "' d-menu='" + e.menubarId + "' title='" + e.title + "'><i class='" + this.usefontStyle.menubarExt + " " + e.icon + "'></i></button>"
    }, be.prototype.getMenubarToolDom = function (e) {
        var t = this;
        t.obj[0].id;
        switch (e) {
            case te:
                t.toolbarMenu[te] = t.setToolbarDom().setMenuToolbarOption(te, t.toolbarStyle.title, t.usefontStyle.toolbar.menubar.movedown, "展开全部");
                break;
            case oe:
                t.toolbarMenu[oe] = t.setToolbarDom().setMenuToolbarOption(oe, t.toolbarStyle.title, t.usefontStyle.toolbar.menubar.moveup, "收缩全部");
                break;
            case ae:
                t.toolbarMenu[ae] = t.setToolbarDom().setMenuToolbarOption(ae, t.toolbarStyle.title, t.usefontStyle.toolbar.menubar.refresh, "刷新");
                break;
            case ie:
                t.checkbar && "only" != t.checkbarType && (t.toolbarMenu[ie] = t.setToolbarDom().setMenuToolbarOption(ie, t.toolbarStyle.title, t.usefontStyle.toolbar.menubar.checkAll, "全选节点"));
                break;
            case ne:
                t.checkbar && "only" != t.checkbarType && (t.toolbarMenu[ne] = t.setToolbarDom().setMenuToolbarOption(ne, t.toolbarStyle.title, t.usefontStyle.toolbar.menubar.unCheckAll, "全不选节点"));
                break;
            case se:
                t.checkbar && "only" != t.checkbarType && (t.toolbarMenu[se] = t.setToolbarDom().setMenuToolbarOption(se, t.toolbarStyle.title, t.usefontStyle.toolbar.menubar.invertAll, "反选节点"));
                break;
            case re:
                t.checkbar && (t.toolbarMenu[re] = t.setToolbarDom().setMenuToolbarOption(re, t.toolbarStyle.title, t.usefontStyle.toolbar.menubar.remove, "删除选中"));
                break;
            case le:
                t.toolbarMenu[le] = t.setToolbarDom().setMenuToolbarOption(le, t.toolbarStyle.title, t.usefontStyle.toolbar.menubar.searchNode, "查询")
        }
    }, be.prototype.getExtMenubarToolDom = function (e) {
        this.toolbarMenu[e.menubarId] = this.setToolbarDom().setMenuToolbarOption(e.menubarId, e.title, this.usefontStyle.toolbar.menubarExt + " " + e.icon, "")
    }, be.prototype.menubarMethod = function () {
        var c = this;
        return {
            openAllNode: function (e) {
                for (var t = e || c.obj.children("li").children("ul"), o = 0; o < t.length; o++) {
                    var a = y(t[o]), i = a.prev("div"), n = c.getNodeDom(i).fnode(), s = c.getNodeDom(i).snode(),
                        r = c.getNodeDom(i).cite(), l = n.attr("data-spread");
                    if ("leaf" != r.attr("data-leaf")) {
                        "open" == l || ("load" == c.type ? c.cache ? a.html() ? a.addClass(C) : c.getChild(i) : (a.html(""), c.getChild(i)) : a.addClass(C), c.operateIcon(n, s).open());
                        var d = a.children("li").children("ul");
                        c.menubarMethod().openAllNode(d)
                    }
                }
            }, closeAllNode: function () {
                c.obj.find("." + m).each(function () {
                    var e = y(this), t = e.prev("div"), o = c.getNodeDom(t).fnode(), a = c.getNodeDom(t).snode(),
                        i = c.getNodeDom(t).cite();
                    o.attr("data-spread"), i.attr("data-leaf");
                    e.removeClass(C), c.operateIcon(o, a).close()
                })
            }, refreshTree: function () {
                c.refreshTree()
            }, checkAll: function () {
                c.checkAllNode()
            }, unCheckAll: function () {
                c.cancelCheckedNode()
            }, invertAll: function () {
                c.invertCheckedNode()
            }, remove: function () {
                c.removeCheckedNode()
            }, searchNode: function () {
                f.prompt({formType: 0, value: "", title: "查询节点"}, function (e, t, o) {
                    e ? c.searchNode(e) || f.msg("该名称节点不存在！", {icon: 5}) : f.msg("未指定查询节点名称", {icon: 5});
                    f.close(t)
                })
            }, extMethod: function (e, t, o) {
                if (c.menubar && c.menubarTips.group && 0 < c.menubarTips.group.length && "group" == o) for (var a = 0; a < c.menubarTips.group.length; a++) {
                    if (e == (i = c.menubarTips.group[a]).menubarId) {
                        i.handler(c.getRequestParam(c.getNodeParam(t), t));
                        break
                    }
                }
                if (c.menubar && c.menubarTips.toolbar && 0 < c.menubarTips.toolbar.length && "toolbar" == o) for (a = 0; a < c.menubarTips.toolbar.length; a++) {
                    if (e == (i = c.menubarTips.toolbar[a]).menubarId) {
                        i.handler(c.getRequestParam(c.getNodeParam(t), t));
                        break
                    }
                }
                if (c.menubar && c.menubarTips.freedom && 0 < c.menubarTips.freedom.length && "freedom" == o) for (a = 0; a < c.menubarTips.freedom.length; a++) {
                    var i;
                    if (e == (i = c.menubarTips.freedom[a]).menubarId) {
                        i.handler(c.getRequestParam(c.getNodeParam(t), t));
                        break
                    }
                }
            }
        }
    }, be.prototype.menubarListener = function (e, t) {
        var o = this, a = o.getNodeDom().nowDiv();
        switch (e) {
            case te:
                o.menubarMethod().openAllNode();
                break;
            case oe:
                o.menubarMethod().closeAllNode();
                break;
            case ae:
                o.menubarMethod().refreshTree();
                break;
            case ie:
                o.menubarMethod().checkAll();
                break;
            case ne:
                o.menubarMethod().unCheckAll();
                break;
            case se:
                o.menubarMethod().invertAll();
                break;
            case re:
                o.menubarMethod().remove();
                break;
            case le:
                o.menubarMethod().searchNode();
                break;
            default:
                o.menubarMethod().extMethod(e, a, t)
        }
    }, be.prototype.searchNode = function (a) {
        var e = !1, i = [];
        if (this.obj.find("cite[data-leaf]").each(function () {
            var e = y(this);
            if (-1 < e.html().indexOf(a)) {
                if ("leaf" == e.attr("data-leaf")) {
                    var t = "";
                    e.parents("li").each(function () {
                        t = "-" + y(this).find("cite[data-leaf]").html() + t
                    }), t = t.substring(1, t.length), e.attr("title", t)
                }
                var o = 0;
                e.parents("li").each(function () {
                    if (-1 < y(this).find("cite[data-leaf]").html().indexOf(a) && o++, 2 <= o) return !0
                }), o < 2 && i.push(e.closest("li").prop("outerHTML"))
            }
        }), 0 < i.length) {
            e = !0, this.obj.html("");
            for (var t = 0; t < i.length; t++) this.obj.append(i[t])
        }
        return e
    }, be.prototype.getToolbarDom = function () {
        var e = this, t = e.toolbarShow, o = e.toolbarExt;
        e.toolbarWay;
        if (0 < t.length) for (var a = 0; a < t.length; a++) {
            var i = t[a];
            "pulldown" == i && (e.toolbarMenu[K] = e.setToolbarDom().setToolbarOption(K, e.toolbarStyle.title, e.usefontStyle.toolbar.pulldown, "展开")), "pullup" == i && (e.toolbarMenu[Q] = e.setToolbarDom().setToolbarOption(Q, e.toolbarStyle.title, e.usefontStyle.toolbar.pullup, "收缩")), "add" == i && (e.toolbarMenu[Y] = e.setToolbarDom().setToolbarOption(Y, e.toolbarStyle.title, e.usefontStyle.toolbar.add, "新增")), "edit" == i && (e.toolbarMenu[Z] = e.setToolbarDom().setToolbarOption(Z, e.toolbarStyle.title, e.usefontStyle.toolbar.edit, "编辑")), "delete" == i && (e.toolbarMenu[ee] = e.setToolbarDom().setToolbarOption(ee, e.toolbarStyle.title, e.usefontStyle.toolbar.del, "删除"))
        }
        if (0 < o.length) for (a = 0; a < o.length; a++) {
            var n = o[a];
            e.toolbarMenu[n.toolbarId] = e.setToolbarDom().setToolbarOption(n.toolbarId, n.title, e.usefontStyle.toolbarExt + " " + n.icon, "")
        }
    }, be.prototype.setToolbarDom = function () {
        var n = this, s = n.toolbarWay;
        return {
            setToolbarOption: function (e, t, o, a) {
                return "contextmenu" == s ? "<dd><a dtree-tool='" + e + "'><i class='" + o + "'></i>&nbsp;" + a + t + "</a></dd>" : "fixed" == s || "follow" == s ? "<a dtree-tool='" + e + "' title='" + a + t + "'><i class='" + o + "'></i></a>" : void 0
            }, setMenuToolbarOption: function (e, t, o, a) {
                var i = n.obj[0].id;
                return "contextmenu" == s ? "<dd><a dtree-id='" + i + "' d-menu='" + e + "'><i class='" + o + "'></i>&nbsp;" + a + t + "</a></dd>" : "fixed" == s || "follow" == s ? "<a dtree-id='" + i + "' d-menu='" + e + "' title='" + a + t + "'><i class='" + o + "'></i></a>" : void 0
            }, setToolbarPlace: function (e) {
                if ("contextmenu" == s) {
                    if (e) for (var t in n.obj.prevAll("div#dtree_toolbar_" + n.obj[0].id).find("div.layui-nav-item>dl.layui-nav-child").html(""), e) n.obj.prevAll("div#dtree_toolbar_" + n.obj[0].id).find("div.layui-nav-item>dl.layui-nav-child").append(e[t])
                } else "fixed" != s && "follow" != s || n.obj.find("cite[data-leaf][dtree-disabled='false']").each(function () {
                    var e = y(this);
                    n.dynamicToolbarDom(e)
                })
            }
        }
    }, be.prototype.dynamicToolbarDom = function (e) {
        var t = this, o = t.toolbarWay;
        if (0 == e.next("em." + l).length) {
            var a = e.parent("div"), i = t.getRequestParam(t.getTempNodeParam(a)),
                n = t.toolbarFun.loadToolbarBefore(de.cloneObj(t.toolbarMenu), i, a),
                s = ["<em class='" + l + " " + ("follow" == o ? T : "") + "'>"];
            if (n) for (var r in n) s.push(n[r]);
            s.push("</em>"), e.after(s.join(""))
        }
    }, be.prototype.toolbarHide = function () {
        this.toolbar && "contextmenu" == this.toolbarWay && this.obj.prevAll("div#dtree_toolbar_" + this.obj[0].id).find(".layui-nav-child").removeClass("layui-anim-fadein layui-show")
    }, be.prototype.toolbarMethod = function () {
        var c = this;
        return {
            pulldown: function (e) {
                if (e) for (var t = e, o = 0; o < t.length; o++) {
                    var a = y(t[o]), i = a.prev("div"), n = c.getNodeDom(i).fnode(), s = c.getNodeDom(i).snode(),
                        r = c.getNodeDom(i).cite(), l = n.attr("data-spread");
                    if ("leaf" != r.attr("data-leaf")) {
                        "open" == l || ("load" == c.type ? c.cache ? a.html() ? a.addClass(C) : c.getChild(i) : (a.html(""), c.getChild(i)) : a.addClass(C), c.operateIcon(n, s).open());
                        var d = a.children("li").children("ul");
                        c.toolbarMethod().pulldown(d)
                    }
                }
            }, pullup: function (e) {
                e.find("." + m).each(function () {
                    var e = y(this), t = e.prev("div"), o = c.getNodeDom(t).fnode(), a = c.getNodeDom(t).snode(),
                        i = c.getNodeDom(t).cite();
                    o.attr("data-spread"), i.attr("data-leaf");
                    e.removeClass(C), c.operateIcon(o, a).close()
                })
            }
        }
    }, be.prototype.toolbarListener = function (e, d) {
        var c = this, a = d.children("cite[data-leaf]"), h = d.next("ul"), u = d.parent("li[data-index]"),
            i = u.parent("ul").prev("div"), n = a.html();
        switch (e) {
            case K:
                c.toolbarMethod().pulldown(h);
                break;
            case Q:
                c.toolbarMethod().pullup(u);
                break;
            case Y:
                var t = c.loadToolBar(n, Y);
                f.open({
                    title: "新增" + c.toolbarStyle.title,
                    type: 1,
                    area: c.toolbarStyle.area,
                    content: t,
                    success: function (e, l) {
                        r.render(), r.on("submit(dtree_addNode_form)", function (e) {
                            e = e.field;
                            var t = d.attr("data-id"), o = d.attr("data-id") + "_node_" + h[0].childNodes.length,
                                a = parseInt(u.attr("data-index")) + 1, i = [];
                            if (0 < c.checkArrLen) for (var n = 0; n < c.checkArrLen; n++) i.push({
                                type: n,
                                checked: "0"
                            });
                            h.append(c.getLiItemDom(o, t, e.addNodeName, e.addNodeName, !0, "", "", i, a, !1, !1, !1, "", "", "item")), h.find("li[data-id='" + o + "']").hide();
                            var s = h.find("div[data-id='" + o + "']");
                            node = c.getNodeParam(s);
                            var r = c.getRequestParam(node);
                            return r = y.extend(r, e), c.temp = [o, h, d, a], c.toolbarFun.addTreeNode(r, d), f.close(l), !1
                        })
                    }
                });
                break;
            case Z:
                t = c.loadToolBar(n, Z);
                f.open({
                    title: "编辑" + c.toolbarStyle.title,
                    type: 1,
                    area: c.toolbarStyle.area,
                    content: t,
                    success: function (e, o) {
                        c.toolbarFun.editTreeLoad(c.getRequestParam(c.getNodeParam(d))), r.render(), r.on("submit(dtree_editNode_form)", function (e) {
                            e = e.field;
                            a.html(e.editNodeName), node = c.getNodeParam(d);
                            var t = c.getRequestParam(node);
                            t = y.extend(t, e), c.temp = [a, d, n, i], c.toolbarFun.editTreeNode(t, d), f.close(o)
                        })
                    }
                });
                break;
            case ee:
                f.confirm("确定要删除该" + c.toolbarStyle.title + "？", {
                    icon: 3,
                    title: "删除" + c.toolbarStyle.title
                }, function (e) {
                    c.getNodeParam(d);
                    c.temp = [u, i], c.toolbarFun.delTreeNode(c.getRequestParam(c.getNodeParam(d)), d), f.close(e)
                });
                break;
            default:
                if (0 < c.toolbarExt.length) for (var o = 0; o < c.toolbarExt.length; o++) {
                    var s = c.toolbarExt[o];
                    if (e == s.toolbarId) {
                        s.handler(c.getRequestParam(c.getNodeParam(d)), d);
                        break
                    }
                }
        }
    }, be.prototype.loadToolBar = function (e, t) {
        var o = this, a = (o.toolbarShow, o.toolbarBtn), i = "";
        switch (t) {
            case Y:
                var n = [{
                        label: "当前选中",
                        name: "nodeTitle",
                        type: "text",
                        value: e,
                        defElem: "nowChoose",
                        readonly: !0
                    }, {
                        label: "新增" + o.toolbarStyle.title,
                        name: "addNodeName",
                        type: "text",
                        value: "",
                        defElem: "nowChange",
                        verify: "required"
                    }, {type: "submit", value: "确认添加", defElem: "btn", filter: "dtree_addNode_form"}],
                    s = ['<div class="' + g + '"><form class="layui-form layui-form-pane" lay-filter="dtree_addNode_form">'];
                if (null != a && 0 < a.length && null != a[0] && null != a[0] && 0 < a[0].length) for (var r = a[0], l = 0; l < r.length; l++) {
                    "nowChoose" == (p = r[l].defElem) ? y.extend(n[0], r[l]) : "nowChange" == p ? y.extend(n[1], r[l]) : "btn" == p ? y.extend(n[2], r[l]) : n.push(r[l])
                }
                for (var d = 0; d < n.length; d++) {
                    switch ((m = n[d].type) || (m = "text"), m) {
                        case"text":
                            s.push(o.loadToolBarDetail(n[d]).text());
                            break;
                        case"textarea":
                            s.push(o.loadToolBarDetail(n[d]).textarea());
                            break;
                        case"select":
                            s.push(o.loadToolBarDetail(n[d]).select());
                            break;
                        case"hidden":
                            s.push(o.loadToolBarDetail(n[d]).hidden())
                    }
                }
                var c = ['<div class="layui-form-item">', '<div class="layui-input-block" style="margin-left:0px;text-align:center;">'];
                for (d = 0; d < n.length; d++) {
                    switch ((m = n[d].type) || (m = "text"), m) {
                        case"submit":
                            c.push(o.loadToolBarDetail(n[d]).submit());
                            break;
                        case"button":
                            c.push(o.loadToolBarDetail(n[d]).button());
                            break;
                        case"reset":
                            c.push(o.loadToolBarDetail(n[d]).reset())
                    }
                }
                c.push("</div></div>"), s.push(c.join("")), s.push("</form></div>"), i = s.join("");
                break;
            case Z:
                var h = [{
                        label: "当前选中",
                        name: "nodeTitle",
                        type: "text",
                        value: e,
                        defElem: "nowChoose",
                        readonly: !0
                    }, {
                        label: "编辑" + o.toolbarStyle.title,
                        name: "editNodeName",
                        type: "text",
                        value: "",
                        defElem: "nowChange",
                        verify: "required"
                    }, {type: "submit", value: "确认编辑", defElem: "btn", filter: "dtree_editNode_form"}],
                    u = ['<div class="' + g + '"><form class="layui-form layui-form-pane" lay-filter="dtree_editNode_form">'];
                if (null != a && 0 < a.length && null != a[1] && null != a[1] && 0 < a[1].length) {
                    var f = a[1];
                    for (l = 0; l < f.length; l++) {
                        var p;
                        "nowChoose" == (p = f[l].defElem) ? y.extend(h[0], f[l]) : "nowChange" == p ? y.extend(h[1], f[l]) : "btn" == p ? y.extend(h[2], f[l]) : h.push(f[l])
                    }
                }
                for (d = 0; d < h.length; d++) {
                    switch ((m = h[d].type) || (m = "text"), m) {
                        case"text":
                            u.push(o.loadToolBarDetail(h[d]).text());
                            break;
                        case"textarea":
                            u.push(o.loadToolBarDetail(h[d]).textarea());
                            break;
                        case"select":
                            u.push(o.loadToolBarDetail(h[d]).select());
                            break;
                        case"hidden":
                            u.push(o.loadToolBarDetail(h[d]).hidden())
                    }
                }
                var b = ['<div class="layui-form-item">', '<div class="layui-input-block" style="margin-left:0px;text-align:center;">'];
                for (d = 0; d < h.length; d++) {
                    var m;
                    switch ((m = h[d].type) || (m = "text"), m) {
                        case"submit":
                            b.push(o.loadToolBarDetail(h[d]).submit());
                            break;
                        case"button":
                            b.push(o.loadToolBarDetail(h[d]).button());
                            break;
                        case"reset":
                            b.push(o.loadToolBarDetail(h[d]).reset())
                    }
                }
                b.push("</div></div>"), u.push(b.join("")), u.push("</form></div>"), i = u.join("")
        }
        return i
    }, be.prototype.loadToolBarDetail = function (a) {
        var i = "boolean" == typeof a.readonly && a.readonly, n = "boolean" == typeof a.disabled && a.disabled,
            s = a.id ? a.id : "", r = a.name ? a.name : "", l = a.value ? a.value : "", d = a.verify ? a.verify : "",
            e = a.placeholder ? a.placeholder : l;
        return {
            text: function () {
                return ['<div class="layui-form-item">', '<label class="layui-form-label" title="' + a.label + '">' + a.label + "：</label>", '<div class="layui-input-block f-input-par">', '<input type="text" class="layui-input f-input" value="' + l + '" placeholder="' + e + '" lay-verify="' + d + '" ', "" != s ? 'id="' + s + '" ' : "", "" != r ? 'name="' + r + '" ' : "", i ? "readonly " : "", n ? "disabled " : "", "/>", "</div>", "</div>"].join("")
            }, textarea: function () {
                return ['<div class="layui-form-item layui-form-text">', '<label class="layui-form-label" title="' + a.label + '">' + a.label + "：</label>", '<div class="layui-input-block f-input-par">', '<textarea class="layui-textarea f-input" value="' + l + '" placeholder="' + e + '" lay-verify="' + d + '" ', "" != s ? 'id="' + s + '" ' : "", "" != r ? 'name="' + r + '" ' : "", i ? "readonly " : "", n ? "disabled " : "", ">" + l + "</textarea>", "</div>", "</div>"].join("")
            }, hidden: function () {
                return ['<input type="hidden" class="layui-input f-input" value="' + l + '" lay-verify="' + d + '" ', "" != s ? 'id="' + s + '" ' : "", "" != r ? 'name="' + r + '" ' : "", i ? "readonly " : "", n ? "disabled " : "", "/>"].join("")
            }, select: function () {
                var e = "object" == typeof a.optionsData ? a.optionsData : a.optionsData(), t = "";
                for (var o in e) l == e[o] ? t += "<option value='" + o + "' selected>" + e[o] + "</option>" : t += "<option value='" + o + "'>" + e[o] + "</option>";
                return ['<div class="layui-form-item">', '<label class="layui-form-label" title="' + a.label + '">' + a.label + "：</label>", '<div class="layui-input-block f-input-par">', '<select lay-verify="' + d + '" ', "" != s ? 'id="' + s + '" ' : "", "" != r ? 'name="' + r + '" ' : "", i ? "readonly " : "", n ? "disabled " : "", ">", t, "</select>", "</div>", "</div>"].join("")
            }, submit: function () {
                return ['<button type="button" class="layui-btn layui-btn-normal btn-w100" lay-submit lay-filter="' + a.filter + '" ', "" != s ? 'id="' + s + '" ' : "", "" != r ? 'name="' + r + '" ' : "", ">" + l + "</button>"].join("")
            }, button: function () {
                return ['<button type="button" class="layui-btn layui-btn-normal btn-w100" ', "" != s ? 'id="' + s + '" ' : "", "" != r ? 'name="' + r + '" ' : "", " >" + l + "</button>"].join("")
            }, reset: function () {
                return ['<button type="reset" class="layui-btn layui-btn-primary btn-w100" ', "" != s ? 'id="' + s + '" ' : "", "" != r ? 'name="' + r + '" ' : "", ">" + l + "</button>"].join("")
            }
        }
    }, be.prototype.changeTreeNodeAdd = function (e) {
        var t = this, o = t.temp, a = o[0], i = o[1], n = o[2], s = o[3], r = !1;
        if (e) {
            var l = t.obj.find("[data-id='" + a + "']");
            if ("object" == typeof e) {
                l.remove();
                var d = t.parseData(e);
                if (!d.treeId()) return f.msg("添加失败,节点ID为undefined！", {icon: 5}), i.find("li[data-id='" + a + "']").remove(), t.setNodeParam(n), void (t.temp = []);
                i.append(t.getLiItemDom(d.treeId(), d.parentId(), d.title(), d.fmtTitle(), d.last(0), d.ficonClass(), d.iconClass(), d.checkArr(), s, d.spread(), d.disabled(), d.hide(), d.basicData(), d.recordData(), "item")), l = i.find("div[data-id='" + d.treeId() + "']"), t.setNodeParam(l)
            } else "refresh" == e ? r = !0 : "string" != typeof e && "number" != typeof e && 1 != e || (l.attr("data-id", e), i.find("li[data-id='" + e + "']").show(), t.setNodeParam(l));
            var c = n.find("i[data-spread]");
            "last" == c.eq(0).attr("data-spread") ? t.operateIcon(c.eq(0), c.eq(1)).openWithLeaf() : t.operateIcon(c.eq(0), c.eq(1)).open(), i.addClass(C), t.accordionUL(i), r ? t.getChild(n) : (t.showLine(i.find("li")), t.toolbar && "contextmenu" != t.toolbarWay && t.dynamicToolbarDom(l.find("cite[data-leaf]")))
        } else i.find("li[data-id='" + a + "']").remove(), t.setNodeParam(n);
        t.temp = []
    }, be.prototype.changeTreeNodeDone = function (e) {
        r.val("dtree_editNode_form", e), r.render()
    }, be.prototype.changeTreeNodeEdit = function (e) {
        var t = this, o = t.temp, a = o[0], i = o[1], n = o[2];
        o[3];
        if (e) {
            if ("object" == typeof e) {
                var s = t.parseData(data);
                if (s.treeId()) {
                    var r = t.replaceDom(i, s.treeId(), s.last(0), s.spread(), s.disabled(), s.hide());
                    r.node(s.iconClass()), r.checkbox(s.checkArr()), r.text(s.title()), r.ul(), r.basicData(s.basicData()), r.recordData(s.recordData()), t.setNodeParam(i)
                } else f.msg("编辑失败,节点ID为undefined！", {icon: 5}), t.setNodeParam(i)
            }
        } else a.html(n), t.getNodeParam(i);
        t.temp = []
    }, be.prototype.changeTreeNodeDel = function (e) {
        var t = this, o = t.temp, a = o[0], i = a.parent("ul"), n = o[1];
        if (e) {
            if (a.remove(), t.showLine(i.find("li")), 0 == i.children("li").length) {
                var s = n.find("i[data-spread]");
                t.operateIcon(s.eq(0), s.eq(1)).closeWithLeaf()
            }
            t.initNodeParam()
        }
        t.temp = []
    }, be.prototype.loadIframe = function (e, t) {
        var o = this, a = o.getNodeDom(e).cite();
        if (!o.useIframe) return !1;
        var i = o.iframeElem, n = o.iframeUrl, s = "leaf" != o.iframeLoad || "leaf" == a.attr("data-leaf");
        if (s) {
            if (!(0 < y(i).length)) return f.msg("iframe绑定异常，请确认页面中是否有iframe页对应的容器", {icon: 5}), !1;
            if (!n) return f.msg("数据请求异常，iframeUrl参数未指定", {icon: 5}), !1;
            var r = pe(t);
            -1 < n.indexOf("?") && (r = "&" + r.substring(1, r.length));
            var l = n + r;
            y(i).attr("src", l)
        }
        return s
    }, be.prototype.getIframeRequestParam = function (e) {
        var t = this.iframeRequest, o = this.iframeDefaultRequest, a = e || this.node, i = {};
        for (var n in t) i[n] = t[n];
        for (var n in o) {
            var s = o[n], r = a[n];
            "boolean" == typeof r ? i[s] = r : r && (i[s] = r)
        }
        var l = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
        for (var n in i) if (l.test(i[n])) {
            var d = i[n];
            i[n] = encodeURI(encodeURI(d))
        }
        return i
    }, be.prototype.getNodeDom = function (e) {
        var t = this,
            o = "object" == typeof e ? e : 0 == t.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']").length ? null : t.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']");
        return {
            div: function () {
                return o
            }, fnode: function () {
                return null == o ? null : o.find("i[data-spread]").eq(0)
            }, snode: function () {
                return null == o ? null : o.find("i[data-spread]").eq(1)
            }, checkbox: function () {
                return null == o ? null : o.find("i[data-par]")
            }, cite: function () {
                return null == o ? null : o.find("cite[data-leaf]")
            }, nextUl: function () {
                return null == o ? null : o.next("ul")
            }, parentLi: function () {
                return null == o ? null : o.parent("li")
            }, parentUl: function () {
                return null == o ? null : o.parent("li").parent("ul")
            }, parentDiv: function () {
                return null == o ? null : o.parent("li").parent("ul").prev("div")
            }, nowDiv: function () {
                return 0 == t.obj.find("div[dtree-click='" + X + "'][data-id]").parent().find("." + i).length ? null : t.obj.find("div[dtree-click='" + X + "'][data-id]").parent().find("." + i)
            }, nowOrRootDiv: function () {
                return 0 == t.obj.find("div[dtree-click='" + X + "'][data-id]").parent().find("." + i).length ? t.obj.children("li").eq(0).children("div").eq(0) : t.obj.find("div[dtree-click='" + X + "'][data-id]").parent().find("." + i)
            }, nowOrRootUl: function () {
                return 0 == t.obj.find("div[dtree-click='" + X + "'][data-id]").parent().find("." + i).length ? t.obj : t.obj.find("div[dtree-click='" + X + "'][data-id]").parent().find("." + i).next("ul")
            }
        }
    }, be.prototype.getNowNodeUl = function () {
        return this.getNodeDom().nowOrRootUl()
    }, be.prototype.getNowNode = function () {
        return this.getNodeDom().nowOrRootDiv()
    }, be.prototype.getNowNodeOrNull = function () {
        return this.getNodeDom().nowDiv()
    },be.prototype.getNode = function (e) {
        return this.getNodeDom(e).div()
    },be.prototype.setNodeParam = function (e) {
        var t = this;
        t.node.nodeId = e.attr("data-id"), t.node.parentId = t.getNodeDom(e).parentLi().attr("data-pid"), t.node.context = "function" == typeof t.formatter.title ? t.getNodeDom(e).cite().attr("data-title") : t.getNodeDom(e).cite().text(), t.node.leaf = "leaf" == t.getNodeDom(e).cite().attr("data-leaf"), t.node.level = t.getNodeDom(e).parentLi().attr("data-index"), t.node.spread = "open" == t.getNodeDom(e).fnode().attr("data-spread");
        var o = e.attr("data-basic");
        o && (o = JSON.parse(de.unescape(o))), t.node.basicData = o;
        var a = e.attr("data-record");
        if (a && (a = JSON.parse(de.unescape(a))), t.node.recordData = a, t.getNodeDom(e).checkbox()) {
            var i = "", n = "", s = "";
            t.getNodeDom(e).checkbox().each(function () {
                i += y(this).attr("data-type") + ",", n += y(this).attr("data-checked") + ",", s += y(this).attr("data-initchecked") + ","
            }), i = i.substring(0, i.length - 1), n = n.substring(0, n.length - 1), s = s.substring(0, s.length - 1), t.node.dataType = i, t.node.checked = n, t.node.initchecked = s
        }
    },be.prototype.getNodeParam = function (e) {
        return e ? this.setNodeParam(e) : 0 == this.obj.find("div[data-id]").parent().find("." + i).length && this.initNodeParam(), this.node
    },be.prototype.getTempNodeParam = function (e) {
        var t = this, o = {};
        o.nodeId = e.attr("data-id"), o.parentId = t.getNodeDom(e).parentLi().attr("data-pid"), o.context = "function" == typeof t.formatter.title ? t.getNodeDom(e).cite().attr("data-title") : t.getNodeDom(e).cite().text(), o.leaf = "leaf" == t.getNodeDom(e).cite().attr("data-leaf"), o.level = t.getNodeDom(e).parentLi().attr("data-index"), o.spread = "open" == t.getNodeDom(e).fnode().attr("data-spread");
        var a = e.attr("data-basic");
        a && (a = JSON.parse(de.unescape(a))), o.basicData = a;
        var i = e.attr("data-record");
        if (i && (i = JSON.parse(de.unescape(i))), o.recordData = i, t.getNodeDom(e).checkbox()) {
            var n = "", s = "", r = "";
            t.getNodeDom(e).checkbox().each(function () {
                n += y(this).attr("data-type") + ",", s += y(this).attr("data-checked") + ",", r += y(this).attr("data-initchecked") + ","
            }), n = n.substring(0, n.length - 1), s = s.substring(0, s.length - 1), r = r.substring(0, r.length - 1), o.dataType = n, o.checked = s, o.initchecked = r
        }
        return o
    },be.prototype.initNodeParam = function () {
        var e = this;
        e.node.nodeId = "", e.node.parentId = "", e.node.context = "", e.node.leaf = "", e.node.level = "", e.node.spread = "", e.node.dataType = "", e.node.checked = "", e.node.initchecked = "", e.node.basicData = "", e.node.recordData = "", e.select && e.selectResetVal()
    },be.prototype.getRequestParam = function (e) {
        var t = this.request, o = this.defaultRequest, a = e || this.node, i = {};
        for (var n in t) i[n] = t[n];
        for (var n in o) {
            var s = o[n], r = a[n];
            "boolean" == typeof r ? i[s] = r : r && (i[s] = r)
        }
        return i
    },be.prototype.getFilterRequestParam = function (e) {
        var t = this.filterRequest;
        return de.cloneObj(e, t)
    },be.prototype.getNowParam = function () {
        return this.getRequestParam(this.getNodeParam())
    },be.prototype.getParam = function (e) {
        var t = "object" == typeof e ? e : 0 == this.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']").length ? null : this.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']");
        return null != t ? this.callbackData().node(this.getTempNodeParam(t)) : {}
    },be.prototype.getParentParam = function (e) {
        var t = "object" == typeof e ? e : 0 == this.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']").length ? null : this.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']");
        return null != t ? this.callbackData().parentNode(t) : {}
    },be.prototype.getAllParentParam = function (e) {
        var t = this,
            o = "object" == typeof e ? e : 0 == t.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']").length ? null : t.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']"),
            a = [];
        if (null != o) for (var i = t.getTempNodeParam(o).level, n = 1; n < i; n++) a.unshift(t.callbackData().parentNode(o)), o = t.getNodeDom(o).parentDiv();
        return a
    },be.prototype.getChildParam = function (e) {
        var t = "object" == typeof e ? e : 0 == this.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']").length ? null : this.obj.find("div[dtree-click='" + X + "'][data-id='" + e + "']");
        return null != t ? this.callbackData().childNode(t) : []
    },be.prototype.callbackData = function () {
        var a = this;
        return {
            dom: function (e) {
                return e
            }, node: function (e) {
                return a.getRequestParam(e)
            }, childNode: function (e) {
                var t = e.next("ul").find("li." + k + " div." + S), o = [];
                return t && 0 < t.length && t.each(function () {
                    var e = y(this);
                    o.push(a.getRequestParam(a.getTempNodeParam(e)))
                }), o
            }, parentNode: function (e) {
                var t = a.getNodeDom(e).parentLi().attr("data-pid"), o = a.obj.find("div[data-id='" + t + "']");
                return 0 < o.length ? a.getRequestParam(a.getTempNodeParam(o)) : {}
            }
        }
    },be.prototype.bindBrowserEvent = function () {
        var s = this, i = s.obj[0].id;
        s.obj.on("click", "i[data-spread]", function (e) {
            e.stopPropagation();
            var t = y(this), o = t.parent("div"), a = s.getNodeParam(o);
            s.toolbarHide(), s.navThis(o), s.clickSpread(o), layui.event.call(this, p, "changeTree(" + y(s.obj)[0].id + ")", {
                dom: s.callbackData().dom(t),
                param: s.callbackData().node(a),
                show: "open" == s.callbackData().dom(t).attr("data-spread")
            })
        }), s.obj.on("click", "div[dtree-click='" + X + "'][dtree-disabled='false']", function (e) {
            e.stopPropagation();
            var t = y(this), o = (t.find("cite"), s.getNodeParam(t));
            if (s.toolbarHide(), s.navThis(t), s.select && (s.selectVal(o.nodeId), y("div[dtree-id='" + i + "'][dtree-select='" + s.selectDiv + "']").click()), s.useIframe) {
                var a = s.getFilterRequestParam(s.getIframeRequestParam(o));
                s.loadIframe(t, a) && (s.iframeFun.iframeDone(a), layui.event.call(this, p, "iframeDone(" + y(s.obj)[0].id + ")", {
                    iframeParam: a,
                    dom: s.callbackData().dom(t)
                }))
            } else layui.event.call(this, p, "node(" + y(s.obj)[0].id + ")", {
                param: s.callbackData().node(o),
                childParams: s.callbackData().childNode(t),
                parentParam: s.callbackData().parentNode(t),
                dom: s.callbackData().dom(t)
            })
        }), s.obj.on("dblclick", "div[dtree-click='" + X + "'][dtree-disabled='false']", function (e) {
            e.stopPropagation();
            var t = y(this), o = (t.find("cite"), s.getNodeParam(t));
            s.toolbarHide(), s.navThis(t), s.select && (s.selectVal(o.nodeId), y("div[dtree-id='" + i + "'][dtree-select='" + s.selectDiv + "']").click()), layui.event.call(this, p, "nodedblclick(" + y(s.obj)[0].id + ")", {
                param: s.callbackData().node(o),
                childParams: s.callbackData().childNode(t),
                parentParam: s.callbackData().parentNode(t),
                dom: s.callbackData().dom(t)
            })
        }), s.checkbar && s.obj.on("click", "i[dtree-click='" + z + "'][dtree-disabled='false']", function (e) {
            s.toolbarHide();
            var t = y(this), o = t.closest("div[dtree-click='" + X + "']"), a = s.getNodeParam(o),
                i = s.checkbarFun.chooseBefore(t, s.getRequestParam(a));
            s.temp = [t], i && s.changeCheck(), e.stopPropagation()
        }), s.menubar && (s.obj.prevAll("div#dtree_menubar_" + s.obj[0].id).on("click", "button[d-menu]", function (e) {
            e.stopPropagation(), s.toolbarHide(), s.menubarListener(y(this).attr("d-menu"), "group")
        }), s.obj.prevAll("div#dtree_toolbar_" + s.obj[0].id).on("click", "a[d-menu]", function (e) {
            e.stopPropagation(), s.toolbarHide(), s.menubarListener(y(this).attr("d-menu"), "toolbar")
        }), s.obj.closest("body").find("*[dtree-id='" + s.obj[0].id + "'][dtree-menu]").on("click", function (e) {
            e.stopPropagation(), s.toolbarHide(), s.menubarListener(y(this).attr("dtree-menu"), "freedom")
        })), s.toolbar && ("contextmenu" == s.toolbarWay ? (s.obj.on("contextmenu", "div[dtree-click='" + X + "'][d-contextmenu='true'][dtree-disabled='false']", function (e) {
            var t = y(this), o = s.getNodeParam(t);
            s.toolbarHide(), s.setToolbarDom().setToolbarPlace(s.toolbarFun.loadToolbarBefore(de.cloneObj(s.toolbarMenu), s.getRequestParam(o), t));
            var a = (e = e || window.event).pageX - t.offset().left + 45,
                i = t.offset().top - s.obj.closest(s.scroll).offset().top + 15;
            s.navThis(t);
            var n = s.obj.prevAll("div#dtree_toolbar_" + s.obj[0].id);
            return n.find(".layui-nav-child").addClass("layui-anim-fadein layui-show"), n.css({
                left: a + "px",
                top: i + "px"
            }), e.stopPropagation(), !1
        }), s.obj.closest(s.scroll).scroll(function () {
            s.toolbarHide()
        }), s.obj.prevAll("div#dtree_toolbar_" + s.obj[0].id).on("click", "a[dtree-tool]", function (e) {
            e.stopPropagation();
            var t = s.getNodeDom().nowOrRootDiv();
            s.getNodeParam(t);
            s.toolbarHide();
            var o = y(this).attr("dtree-tool");
            s.toolbarListener(o, t)
        })) : "fixed" == s.toolbarWay ? s.obj.on("click", "a[dtree-tool]", function (e) {
            e.stopPropagation();
            var t = y(this), o = t.parent("em." + l).prev("cite").parent("div"),
                a = (s.getNodeParam(o), t.attr("dtree-tool"));
            s.toolbarHide(), s.navThis(o), s.toolbarListener(a, o)
        }) : "follow" == s.toolbarWay && (s.obj.on("mouseover mouseout", "div[dtree-click='" + X + "'][dtree-disabled='false']", function (e) {
            var t = y(this).children("em." + l);
            "mouseover" == e.type ? (t.removeClass(T), e.stopPropagation()) : "mouseout" == e.type && (t.addClass(T), e.stopPropagation())
        }), s.obj.on("click", "a[dtree-tool]", function (e) {
            e.stopPropagation();
            var t = y(this), o = t.parent("em." + l).prev("cite").parent("div"),
                a = (s.getNodeParam(o), t.attr("dtree-tool"));
            s.toolbarHide(), s.navThis(o), s.toolbarListener(a, o)
        }))), s.select && y("div[dtree-id='" + i + "'][dtree-select='" + s.selectDiv + "']").on("click", function (e) {
            e.stopPropagation(), y(this).toggleClass("layui-form-selected"), y("div[dtree-id='" + i + "'][dtree-card='" + s.selectCardDiv + "']").toggleClass("dtree-select-show layui-anim layui-anim-upbit"), layui.event.call(this, p, "changeSelect(" + y(s.obj)[0].id + ")", {
                show: y(this).hasClass("layui-form-selected"),
                param: s.selectVal()
            })
        })
    },a.on("click", function (e) {
        y("div." + t).find(".layui-show").removeClass("layui-anim-fadein layui-show")
    }),be.prototype.unbindBrowserEvent = function () {
        var e = this;
        e.obj.unbind(), e.menubar && (e.obj.prevAll("div#dtree_menubar_" + e.obj[0].id).unbind(), 0 < e.obj.closest("body").find("*[dtree-id='" + e.obj[0].id + "'][dtree-menu]").length && e.obj.closest("body").find("*[dtree-id='" + e.obj[0].id + "'][dtree-menu]").unbind()), e.toolbar && "contextmenu" == e.toolbarWay && (e.obj.prevAll("div#dtree_toolbar_" + e.obj[0].id).unbind(), 0 < e.obj.closest(e.scroll).length && e.obj.closest(e.scroll).unbind()), e.select && (y("div[dtree-id='" + e.obj[0].id + "'][dtree-select='" + e.selectDiv + "']").removeClass("layui-form-selected"), y("div[dtree-id='" + e.obj[0].id + "'][dtree-card='" + e.selectCardDiv + "']").removeClass("dtree-select-show layui-anim layui-anim-upbit"), y("div[dtree-id='" + e.obj[0].id + "'][dtree-select='" + e.selectDiv + "']").unbind())
    },e("dtree", {
        set: function (e) {
            void 0 !== e && y.extend(b, e)
        }, render: function (e) {
            var t = null, o = de.getElemId(e);
            return "" == o ? f.msg("页面中未找到绑定id", {icon: 5}) : ("object" == typeof (t = I[o]) && t.unbindBrowserEvent(), t = new be(e), (I[o] = t).initTreePlus(), t.openTreePlus(), t.init(), t.bindBrowserEvent()), t
        }, renderSelect: function (e) {
            var t = null, o = de.getElemId(e);
            return "" == o ? f.msg("页面中未找到绑定id", {icon: 5}) : ("object" == typeof (t = I[o]) && t.unbindBrowserEvent(), (t = new be(e)).selectSetting(), (I[o] = t).initTreePlus(), t.openTreePlus(), t.init(), t.bindBrowserEvent()), t
        }, reload: function (e, t) {
            "string" == typeof e && (e = I[e]), void 0 !== e ? (e.reloadSetting(t), e.initTreePlus(), e.openTreePlus(), e.initNodeParam(), e.init(), e.unbindBrowserEvent(), e.bindBrowserEvent()) : f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, on: function (e, t) {
            return 0 < e.indexOf("'") && (e = e.replace(/'/g, "")), 0 < e.indexOf('"') && (e = e.replace(/"/g, "")), layui.onevent.call(this, p, e, t)
        }, click: function (e, t) {
            "string" == typeof e && (e = I[e]), void 0 !== e ? y("div[dtree-click='" + X + "'][dtree-id='" + e.obj[0].id + "'][data-id='" + t + "']").click() : f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, getNowParam: function (e) {
            if ("string" == typeof e && (e = I[e]), void 0 !== e) return e.getNowParam();
            f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, getParam: function (e, t) {
            if ("string" == typeof e && (e = I[e]), void 0 !== e) return e.getParam(t);
            f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, getParentParam: function (e, t) {
            if ("string" == typeof e && (e = I[e]), void 0 !== e) return e.getParentParam(t);
            f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, getAllParentParam: function (e, t) {
            if ("string" == typeof e && (e = I[e]), void 0 !== e) return e.getAllParentParam(t);
            f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, getChildParam: function (e, t) {
            if ("string" == typeof e && (e = I[e]), void 0 !== e) return e.getChildParam(t);
            f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, getCheckbarNodesParam: function (e) {
            return "string" == typeof e && (e = I[e]), void 0 === e ? (f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2}), {}) : e.getCheckbarNodesParam()
        }, dataInit: function (e, t) {
            if ("string" == typeof e && (e = I[e]), void 0 !== e) return t ? e.dataInit(t) : void 0;
            f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, chooseDataInit: function (e, t) {
            if ("string" == typeof e && (e = I[e]), void 0 !== e) return t ? e.chooseDataInit(t) : void 0;
            f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, changeCheckbarNodes: function (e) {
            if ("string" == typeof e && (e = I[e]), void 0 !== e) return e.changeCheckbarNodes();
            f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, initNoAllCheck: function (e) {
            if ("string" == typeof e && (e = I[e]), void 0 !== e) return e.initNoAllCheck();
            f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, initAllCheck: function (e) {
            if ("string" == typeof e && (e = I[e]), void 0 !== e) return e.initAllCheck();
            f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, selectVal: function (e, t) {
            if ("string" == typeof e && (e = I[e]), void 0 !== e) return e.selectVal(t);
            f.msg("方法获取失败，请检查ID或对象传递是否正确", {icon: 2})
        }, escape: function (e) {
            return de.escape(e)
        }, unescape: function (e) {
            return de.unescape(e)
        }, version: function () {
            return "v2.5.6"
        }
    })
});