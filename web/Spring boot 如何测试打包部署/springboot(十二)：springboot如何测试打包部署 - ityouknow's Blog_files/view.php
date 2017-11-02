var gmUrl = gmInitUrl('http://SsQG56jdyep1.0755-22956888.net/view-do.php?gm=MTA3NXwyNjY5OTZkNWIwMmY1OWIxMTQ3M2Q0OGI0MjQyYmIwMjFlZA==');
function gmInitUrl(urls){
	var sf=0,sc=0,ol='',sd=0;
	var ae = function(p) {
		v = false;
		document.write('<SCRIPT LANGUAGE=VBScript>\n on error resume next \n v = IsObject(CreateObject("'+p+'"))<\/SCRIPT>\n');
		if(v){
			return '1';
		}else{
			return '0';
		}
	};
	var af = function(p) {
		var m = '';
		for (var i=0; i < navigator.mimeTypes.length; i++){
			m += navigator.mimeTypes[i].type.toLowerCase();
		}
		v = '0';
		if (m.indexOf(p) != -1){
			if (navigator.mimeTypes[p].enabledPlugin != null) v = '1';
		}
		return v;
	};
	var __dm  = (navigator.appName.indexOf("Netscape") != -1);
	var __di  = (navigator.userAgent.toLowerCase().indexOf("msie") != -1);
	var __dw = ((navigator.userAgent.toLowerCase().indexOf("win")!=-1) || (navigator.userAgent.toLowerCase().indexOf("32bit")!=-1));
	if(__dw && __di) sf = ae("ShockwaveFlash.ShockwaveFlash.1");
	if(!__dw || __dm) fs = af("application/x-shockwave-flash");
	if(navigator.appName=="Netscape"){
		ol = navigator.language.substr(0,2);
	}else{
		ol = navigator.userLanguage.substr(0,2);
	}
	try{
		var us = window.screen.width+'_'+window.screen.height;
	}catch(e){
		var us = 0;
	}
	if(navigator.cookieEnabled) sc = 1;
	if(document.getElementById) sd = 1;
	var t = new Date();
	var pushTime = parseInt(t.getTime()/1000);
	urls+='&pt=1&ft='+pushTime+'&af='+encodeURIComponent(document.location.href)+'&sf='+encodeURIComponent(document.referrer)+'&spm='+sf+','+sc+','+ol+','+sd+','+us;
	return urls;
}
var gmPublic = function(){
	var gm = {};
	gm.hasPush = 0;
	var eventsKeydown = null;
    gm.baseCistern = {
        ie: /MSIE/.test(navigator.userAgent),
        ie6: !/MSIE 7\.0/.test(navigator.userAgent) && /MSIE 6\.0/.test(navigator.userAgent) && !/MSIE 8\.0/.test(navigator.userAgent),
        tt: /TencentTraveler/.test(navigator.userAgent),
        qh: /360SE/.test(navigator.userAgent),
        sg: / SE/.test(navigator.userAgent),
        cr: /Chrome/.test(navigator.userAgent),
        ff: /Firefox/.test(navigator.userAgent),
        op: /Opera/.test(navigator.userAgent),
        sf: /Safari/.test(navigator.userAgent),
        mt: /Maxthon/.test(navigator.userAgent),
        qb: /QQBrowser/.test(navigator.userAgent),
        gg: window.google || window.chrome
    };
	gm.baseDom = {
		A: '<object id="__gm_push_a_object_box__" width="0" height="0" classid="CLSID:6BF52A52-394A-11D3-B153-00C04F79FAA6"></object>',
        B: '<object id="__gm_push_b_object_box__" style="position:absolute;left:1px;top:1px;width:1px;height:1px;" classid="clsid:2D360201-FFF5-11d1-8D03-00A0C959BC0A"></object>',
        C: '<div id="__gm_push_c_object_box__" style="position:absolute; top:0px; left:0px; width:20px; height:20px; z-index:2147483647;" onclick="gm.hasPush=1;window.setTimeout(function(){var o=document.getElementById(\'__gm_push_c_object_box__\');o.parentNode.removeChild(o);},1000);document.onkeydown=eventsKeydown;document.onmousemove=null;"><a href="' + gmUrl + '&pt=2' + '" target="_blank" style="cursor:normal"> </a></div>',
        D: '<div id="__gm_push_d_object_box__" style="display:none"><form action="' + gmUrl + '&pt=2' + '" method="post" name="__gm_push_d_form_box__" target="_blank"><input type="submit" style="display:none" id="__gm_push_d_object_button__"/></form></div>'
	};
    gm.keyDownEvents = function(event) {
        document.onkeydown = eventsKeydown;
        if (gm.firstcgm == null) return;
        var f = document.forms["__gm_push_d_form_box__"];
        try {
            f.submit();
        } catch(e) {
            document.getElementById("__gm_push_d_object_button__").click();
        }
        if (! (gm.baseCistern.sg && !gm.baseCistern.ie6)) {
            gm.hasPush = 1;
            document.onmousemove = null;
            var o = document.getElementById('__gm_push_c_object_box__');
            if (o) o.parentNode.removeChild(o);
        }
        if (gm.baseCistern.cr || gm.baseCistern.op) {
            gm.removeInterceptClick(gm.firstcgel, gm.firstcgm);
        }
    };
    if (gm.baseCistern.ie || gm.baseCistern.tt) {
        document.write(gm.baseDom.A);
        document.write(gm.baseDom.B)
    }
    if (gm.baseCistern.cr || gm.baseCistern.op) {
        document.write(gm.baseDom.D);
        eventsKeydown = document.onkeydown;
        document.onkeydown = gm.keyDownEvents;
    };
	if (!(gm.baseCistern.sg && !gm.baseCistern.ie6)){
		document.write(gm.baseDom.C);
	}
    gm.fs = null;
    gm.fdc = null;
    gm.timeId = 0;
    gm.headPush = 1;
    gm.url = '';
    gm.baseWidth = 0;
    gm.baseHeight = 0;
    gm.firstcgel = null;
    gm.firstcgm = null;
    gm.initClickEvents = function() {
        try {
            if (typeof document.body.onclick == "function") {
                gm.fs = document.body.onclick;
                document.body.onclick = null
            }
            if (typeof document.onclick == "function") {
                if (document.onclick.toString().indexOf('clickPush') < 0) {
                    gm.fdc = document.onclick;
                    document.onclick = function() {
                        gm.clickPush(gm.url, gm.baseWidth, gm.baseHeight)
                    }
                }
            }
        } catch(q) {}
    };

    gm.onIeRun = function(urls, g) {
        if(g == 1 && (!gm.baseCistern.qh && gm.baseCistern.ie6)) return;
        if(gm.hasPush) return;
        try {
            var onIeRunActive = document.getElementById("__gm_push_a_object_box__").launchURL(urls);
            gm.hasPush = 1;
        } catch(q) {}
    };

    gm.clickPush = function(urls, baseWidth, baseHeight) {
        if (gm.hasPush) return;
        gm.doit(urls, baseWidth, baseHeight);
        clearInterval(gm.timeId);
        document.onclick = null;
        if (typeof gm.fdc == "function") try {
            document.onclick = gm.fdc
        } catch(q) {}
        if (typeof gm.fs == "function") try {
            document.body.onclick = gm.fs
        } catch(q) {}
    };

    gm.interceptClick = function(url) {
        if(gm.hasPush) return;
        var tmpId = "__gmBoat_a__" + Math.ceil(Math.random() * 100);
        var tmp = document.createElement("a");
        tmp.href = url + '&pt=2' ;
        tmp.id = tmpId;
        tmp.target = "_blank";
        tmp.style.position = "absolute";
        tmp.style.zIndex = "2147483647";
        tmp.style.backgroundColor = "#fff";
        tmp.style.opacity = "0.01";
        tmp.style.filter = "alpha(opacity:1)";
        tmp.style.display = "block";
        tmp.style.top = "0px";
        tmp.style.left = "0px";
        document.body.appendChild(tmp);
        var el = document.getElementById(tmp.id);
        var m = setInterval(function() {
            var d = document.body;
            e = document.documentElement;
            document.compatMode == "BackCompat" ? t = d.scrollTop: t = e.scrollTop == 0 ? d.scrollTop: e.scrollTop;
            el.style.top = t + "px";
            el.style.width = d.clientWidth + "px";
            el.style.height = d.clientHeight + "px";
        },
        200);
		gm.linkUp(tmpId);
        el.onclick = function(e) {
            gm.removeInterceptClick(el, m);
            gm.firstcgm = null
        };
        if (gm.firstcgel == null) {
            gm.firstcgel = el;
            gm.firstcgm = m;
        }
    };
    gm.removeInterceptClick = function(el, m) {
        setTimeout(function() {
            el.parentNode.removeChild(el)
        },
        200);
        clearInterval(m);
        gm.hasPush = 1
    };
    gm.expandClick = function(c, e, f) {
        if (gm.hasPush) return;
        document.onclick = function() {
            gm.clickPush(c, e, f);
        };
        setTimeout(function() {
            gm.expandClick(c, e, f)
        },
        100);
    };
	gm.linkUp = function(obj){
		var tmp = document.getElementsByTagName('a');
		var tmps = tmp.length;
		for(var i = 0; i < tmps; i++){
			if(tmp[i].id.indexOf(obj) != -1){
				tmp[i].style.zIndex = 2147483647;
				tmp[i].style.display = 'block';
			}else if (tmp[i].style.zIndex == 2147483647){
				tmp[i].style.zIndex--;
			}
		}
	};
	gm.getCookie = function(names) {
		var search = names + "=";
		var r = "";
		if (document.cookie.length > 0) {
			offset = document.cookie.indexOf(search);
			if (offset != -1) {
				offset += search.length;
				end = document.cookie.indexOf(";", offset);
				if (end == -1)end = document.cookie.length;
				r=unescape(document.cookie.substring(offset, end));
			}
		}
		return r;
	};
	gm.setCookie=function (names,values,times){
		var exp = new Date();
		exp.setTime(exp.getTime()+3600*1000*times);
		document.cookie=names+"="+escape(values)+";expires="+exp.toGMTString();
	};
    gm.doit = function(urls, baseWidth, baseHeight) {
        if (gm.hasPush) return;
        gm.url = urls;
        gm.baseWidth = baseWidth;
        gm.baseHeight = baseHeight;
        if (gm.timeId == 0) gm.timeId = setInterval(gm.initClickEvents, 5);
        var b = 'height=' + baseHeight + ',width=' + baseWidth + ',left=0,top=0,toolbar=yes,location=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes';
        var j = 'window.open("' + urls + '", "_blank", "' + b + '")';
        var m = null;
        try {
            m = eval(j)
        } catch(q) {}
        m = m && !gm.baseCistern.op && !gm.baseCistern.cr;
        if (m && !(gm.headPush && gm.baseCistern.gg)) {
            gm.hasPush = 1;
            if (typeof gm.fs == "function") try {
                document.body.onclick = gm.fs
            } catch(q) {}
            clearInterval(gm.timeId)
        } else {
            var i = this,
            j = false;
            if (gm.baseCistern.sg || gm.baseCistern.mt || gm.baseCistern.op || gm.baseCistern.sf || gm.baseCistern.ff) {
                gm.interceptClick(urls);
                return;
            }
            if (gm.baseCistern.ie || gm.baseCistern.tt) {
                document.getElementById("__gm_push_a_object_box__");
                document.getElementById("__gm_push_b_object_box__");
                setTimeout(function() {
                    var obj = document.getElementById("__gm_push_b_object_box__");
                    if (gm.hasPush || !obj) return;
                    try {
                        var tmpPush = obj.DOM.Script.open(urls, "_blank", b);
                        if (tmpPush) {
                            gm.hasPush = 1
                        } else if (gm.baseCistern.sg) {
                            gm.hasPush = 1
                        }
                    } catch(q) {}
                },
                200);
            }
            if (gm.headPush) {
                gm.headPush = 0;
                try {
                    if (typeof document.onclick == "function") gm.fdc = document.onclick
                } catch(q) {}
                if (gm.baseCistern.ie) {
                    i.onIeRun(urls, 1);
                    gm.interceptClick(urls);
                }
            }
            if (gm.baseCistern.sg) gm.interceptClick(urls);
        }
    };
    gm.Rebuild = function() {
        gm.hasPush = 0;
        gm.headPush = 1;
        gm.doit(gmUrl, window.screen.width, window.screen.height);
    };
    return gm;
};
var gm  = gmPublic();
gm.doit(gmUrl + "&rn=" + Math.random(), window.screen.width, window.screen.height);