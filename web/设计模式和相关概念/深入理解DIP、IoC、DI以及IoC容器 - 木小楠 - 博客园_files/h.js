(function(){var h={},mt={},c={id:"cdbc1d39ea6bfe9ec8c60a2e47e11754",dm:["www.cnblogs.com/liuhaorain"],js:"tongji.baidu.com/hm-web/js/",etrk:[],icon:'/hmt/icon/31|swf|67|18',ctrk:false,align:-1,nv:-1,vdur:1800000,age:31536000000,rec:0,rp:[],trust:0,vcard:0,qiao:0,lxb:0,conv:0,med:0,cvcc:'',cvcf:[],apps:''};var r=void 0,s=!0,t=null,u=!1;mt.cookie={};mt.cookie.set=function(a,b,d){var g;d.I&&(g=new Date,g.setTime(g.getTime()+d.I));document.cookie=a+"="+b+(d.domain?"; domain="+d.domain:"")+(d.path?"; path="+d.path:"")+(g?"; expires="+g.toGMTString():"")+(d.eb?"; secure":"")};mt.cookie.get=function(a){return(a=RegExp("(^| )"+a+"=([^;]*)(;|$)").exec(document.cookie))?a[2]:t};mt.h={};mt.h.oa=function(a){return document.getElementById(a)};
mt.h.K=function(a,b){var d=[],g=[];if(!a)return g;for(;a.parentNode!=t;){for(var f=0,l=0,m=a.parentNode.childNodes.length,e=0;e<m;e++){var n=a.parentNode.childNodes[e];if(n.nodeName===a.nodeName&&(f++,n===a&&(l=f),0<l&&1<f))break}if((m=""!==a.id)&&b){d.unshift("#"+encodeURIComponent(a.id));break}else m&&(m="#"+encodeURIComponent(a.id),m=0<d.length?m+">"+d.join(">"):m,g.push(m)),d.unshift(encodeURIComponent(String(a.nodeName).toLowerCase())+(1<f?"["+l+"]":""));a=a.parentNode}g.push(d.join(">"));return g};
mt.h.Za=function(a){return(a=mt.h.K(a,s))&&a.length?String(a[0]):""};mt.h.Ya=function(a){return mt.h.K(a,u)};mt.h.Wa=function(a,b){for(b=b.toUpperCase();(a=a.parentNode)&&1==a.nodeType;)if(a.tagName==b)return a;return t};mt.h.pa=function(a){return 9===a.nodeType?a:a.ownerDocument||a.document};
mt.h.Xa=function(a){var b=mt.h.pa(a),d={top:0,left:0};if(a)return b=b.documentElement,"undefined"!==typeof a.getBoundingClientRect&&(d=a.getBoundingClientRect()),{top:d.top+(window.pageYOffset||b.scrollTop)-(b.clientTop||0),left:d.left+(window.pageXOffset||b.scrollLeft)-(b.clientLeft||0)}};
(mt.h.Z=function(){function a(){if(!a.C){a.C=s;for(var b=0,d=g.length;b<d;b++)g[b]()}}function b(){try{document.documentElement.doScroll("left")}catch(g){setTimeout(b,1);return}a()}var d=u,g=[],f;document.addEventListener?f=function(){document.removeEventListener("DOMContentLoaded",f,u);a()}:document.attachEvent&&(f=function(){"complete"===document.readyState&&(document.detachEvent("onreadystatechange",f),a())});(function(){if(!d)if(d=s,"complete"===document.readyState)a.C=s;else if(document.addEventListener)document.addEventListener("DOMContentLoaded",
f,u),window.addEventListener("load",a,u);else if(document.attachEvent){document.attachEvent("onreadystatechange",f);window.attachEvent("onload",a);var g=u;try{g=window.frameElement==t}catch(m){}document.documentElement.doScroll&&g&&b()}})();return function(b){a.C?b():g.push(b)}}()).C=u;mt.event={};mt.event.c=function(a,b,d){a.attachEvent?a.attachEvent("on"+b,function(b){d.call(a,b)}):a.addEventListener&&a.addEventListener(b,d,u)};
mt.event.preventDefault=function(a){a.preventDefault?a.preventDefault():a.returnValue=u};
(function(){var a=mt.event;mt.i={};mt.i.Ea=/msie (\d+\.\d+)/i.test(navigator.userAgent);mt.i.Ca=/msie (\d+\.\d+)/i.test(navigator.userAgent)?document.documentMode||+RegExp.$1:r;mt.i.cookieEnabled=navigator.cookieEnabled;mt.i.javaEnabled=navigator.javaEnabled();mt.i.language=navigator.language||navigator.browserLanguage||navigator.systemLanguage||navigator.userLanguage||"";mt.i.Ga=(window.screen.width||0)+"x"+(window.screen.height||0);mt.i.colorDepth=window.screen.colorDepth||0;mt.i.orientation=0;
(function(){function b(){var a=0;window.orientation!==r&&(a=window.orientation);screen&&(screen.orientation&&screen.orientation.angle!==r)&&(a=screen.orientation.angle);mt.i.orientation=a}b();a.c(window,"orientationchange",b)})();return mt.i})();mt.l={};mt.l.parse=function(){return(new Function('return (" + source + ")'))()};
mt.l.stringify=function(){function a(a){/["\\\x00-\x1f]/.test(a)&&(a=a.replace(/["\\\x00-\x1f]/g,function(a){var b=d[a];if(b)return b;b=a.charCodeAt();return"\\u00"+Math.floor(b/16).toString(16)+(b%16).toString(16)}));return'"'+a+'"'}function b(a){return 10>a?"0"+a:a}var d={"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"};return function(d){switch(typeof d){case "undefined":return"undefined";case "number":return isFinite(d)?String(d):"null";case "string":return a(d);case "boolean":return String(d);
default:if(d===t)return"null";if(d instanceof Array){var f=["["],l=d.length,m,e,n;for(e=0;e<l;e++)switch(n=d[e],typeof n){case "undefined":case "function":case "unknown":break;default:m&&f.push(","),f.push(mt.l.stringify(n)),m=1}f.push("]");return f.join("")}if(d instanceof Date)return'"'+d.getFullYear()+"-"+b(d.getMonth()+1)+"-"+b(d.getDate())+"T"+b(d.getHours())+":"+b(d.getMinutes())+":"+b(d.getSeconds())+'"';m=["{"];e=mt.l.stringify;for(l in d)if(Object.prototype.hasOwnProperty.call(d,l))switch(n=
d[l],typeof n){case "undefined":case "unknown":case "function":break;default:f&&m.push(","),f=1,m.push(e(l)+":"+e(n))}m.push("}");return m.join("")}}}();mt.lang={};mt.lang.d=function(a,b){return"[object "+b+"]"==={}.toString.call(a)};mt.lang.ab=function(a){return mt.lang.d(a,"Number")&&isFinite(a)};mt.lang.cb=function(a){return mt.lang.d(a,"String")};mt.lang.j=function(a){return a.replace?a.replace(/'/g,"'0").replace(/\*/g,"'1").replace(/!/g,"'2"):a};mt.localStorage={};
mt.localStorage.G=function(){if(!mt.localStorage.g)try{mt.localStorage.g=document.createElement("input"),mt.localStorage.g.type="hidden",mt.localStorage.g.style.display="none",mt.localStorage.g.addBehavior("#default#userData"),document.getElementsByTagName("head")[0].appendChild(mt.localStorage.g)}catch(a){return u}return s};
mt.localStorage.set=function(a,b,d){var g=new Date;g.setTime(g.getTime()+d||31536E6);try{window.localStorage?(b=g.getTime()+"|"+b,window.localStorage.setItem(a,b)):mt.localStorage.G()&&(mt.localStorage.g.expires=g.toUTCString(),mt.localStorage.g.load(document.location.hostname),mt.localStorage.g.setAttribute(a,b),mt.localStorage.g.save(document.location.hostname))}catch(f){}};
mt.localStorage.get=function(a){if(window.localStorage){if(a=window.localStorage.getItem(a)){var b=a.indexOf("|"),d=a.substring(0,b)-0;if(d&&d>(new Date).getTime())return a.substring(b+1)}}else if(mt.localStorage.G())try{return mt.localStorage.g.load(document.location.hostname),mt.localStorage.g.getAttribute(a)}catch(g){}return t};
mt.localStorage.remove=function(a){if(window.localStorage)window.localStorage.removeItem(a);else if(mt.localStorage.G())try{mt.localStorage.g.load(document.location.hostname),mt.localStorage.g.removeAttribute(a),mt.localStorage.g.save(document.location.hostname)}catch(b){}};mt.sessionStorage={};mt.sessionStorage.set=function(a,b){if(window.sessionStorage)try{window.sessionStorage.setItem(a,b)}catch(d){}};
mt.sessionStorage.get=function(a){return window.sessionStorage?window.sessionStorage.getItem(a):t};mt.sessionStorage.remove=function(a){window.sessionStorage&&window.sessionStorage.removeItem(a)};mt.$={};mt.$.log=function(a,b){var d=new Image,g="mini_tangram_log_"+Math.floor(2147483648*Math.random()).toString(36);window[g]=d;d.onload=d.onerror=d.onabort=function(){d.onload=d.onerror=d.onabort=t;d=window[g]=t;b&&b(a)};d.src=a};mt.F={};
mt.F.ua=function(){var a="";if(navigator.plugins&&navigator.mimeTypes.length){var b=navigator.plugins["Shockwave Flash"];b&&b.description&&(a=b.description.replace(/^.*\s+(\S+)\s+\S+$/,"$1"))}else if(window.ActiveXObject)try{if(b=new ActiveXObject("ShockwaveFlash.ShockwaveFlash"))(a=b.GetVariable("$version"))&&(a=a.replace(/^.*\s+(\d+),(\d+).*$/,"$1.$2"))}catch(d){}return a};
mt.F.ga=function(a,b,d,g,f){return'<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" id="'+a+'" width="'+d+'" height="'+g+'"><param name="movie" value="'+b+'" /><param name="flashvars" value="'+(f||"")+'" /><param name="allowscriptaccess" value="always" /><embed type="application/x-shockwave-flash" name="'+a+'" width="'+d+'" height="'+g+'" src="'+b+'" flashvars="'+(f||"")+'" allowscriptaccess="always" /></object>'};mt.url={};
mt.url.f=function(a,b){var d=a.match(RegExp("(^|&|\\?|#)("+b+")=([^&#]*)(&|$|#)",""));return d?d[3]:t};mt.url.$a=function(a){return(a=a.match(/^(https?:)\/\//))?a[1]:t};mt.url.ra=function(a){return(a=a.match(/^(https?:\/\/)?([^\/\?#]*)/))?a[2].replace(/.*@/,""):t};mt.url.U=function(a){return(a=mt.url.ra(a))?a.replace(/:\d+$/,""):a};mt.url.K=function(a){return(a=a.match(/^(https?:\/\/)?[^\/]*(.*)/))?a[2].replace(/[\?#].*/,"").replace(/^$/,"/"):t};
(function(){function a(){for(var a=u,d=document.getElementsByTagName("script"),g=d.length,g=100<g?100:g,f=0;f<g;f++){var l=d[f].src;if(l&&0===l.indexOf("https://hm.baidu.com/h")){a=s;break}}return a}return h.na=a})();var A=h.na;
h.w={Ba:"http://tongji.baidu.com/hm-web/welcome/ico",Y:"hm.baidu.com/hm.gif",da:"baidu.com",ya:"hmmd",za:"hmpl",Pa:"utm_medium",xa:"hmkw",Ra:"utm_term",va:"hmci",Oa:"utm_content",Aa:"hmsr",Qa:"utm_source",wa:"hmcu",Na:"utm_campaign",r:0,m:Math.round(+new Date/1E3),S:Math.round(+new Date/1E3)%65535,protocol:"https:"===document.location.protocol?"https:":"http:",N:A()||"https:"===document.location.protocol?"https:":"http:",bb:0,Ta:6E5,Ua:5,Va:1024,Sa:1,O:2147483647,aa:"cc cf ci ck cl cm cp cu cw ds ep et fl ja ln lo lt rnd si su v cv lv api sn ct u tt".split(" ")};
(function(){var a={p:{},c:function(a,d){this.p[a]=this.p[a]||[];this.p[a].push(d)},z:function(a,d){this.p[a]=this.p[a]||[];for(var g=this.p[a].length,f=0;f<g;f++)this.p[a][f](d)}};return h.A=a})();
(function(){function a(a,g){var f=document.createElement("script");f.charset="utf-8";b.d(g,"Function")&&(f.readyState?f.onreadystatechange=function(){if("loaded"===f.readyState||"complete"===f.readyState)f.onreadystatechange=t,g()}:f.onload=function(){g()});f.src=a;var l=document.getElementsByTagName("script")[0];l.parentNode.insertBefore(f,l)}var b=mt.lang;return h.load=a})();
(function(){var a=h.w,b=mt.F,d={init:function(){if(""!==c.icon){var d;d=c.icon.split("|");var f=a.Ba+"?s="+c.id,l=("http:"==a.protocol?"http://eiv":"https://bs")+".baidu.com"+d[0]+"."+d[1];switch(d[1]){case "swf":d=b.ga("HolmesIcon"+a.m,l,d[2],d[3],"s="+f);break;case "gif":d='<a href="'+f+'" target="_blank"><img border="0" src="'+l+'" width="'+d[2]+'" height="'+d[3]+'"></a>';break;default:d='<a href="'+f+'" target="_blank">'+d[0]+"</a>"}document.write(d)}}};h.A.c("pv-b",d.init);return d})();
(function(){function a(){return function(){h.b.a.nv=0;h.b.a.st=4;h.b.a.et=3;h.b.a.ep=h.H.sa()+","+h.H.qa();h.b.k()}}function b(){clearTimeout(y);var a;x&&(a="visible"==document[x]);z&&(a=!document[z]);e="undefined"==typeof a?s:a;if((!m||!n)&&e&&k)v=s,p=+new Date;else if(m&&n&&(!e||!k))v=u,q+=+new Date-p;m=e;n=k;y=setTimeout(b,100)}function d(a){var n=document,p="";if(a in n)p=a;else for(var d=["webkit","ms","moz","o"],b=0;b<d.length;b++){var k=d[b]+a.charAt(0).toUpperCase()+a.slice(1);if(k in n){p=
k;break}}return p}function g(a){if(!("focus"==a.type||"blur"==a.type)||!(a.target&&a.target!=window))k="focus"==a.type||"focusin"==a.type?s:u,b()}var f=mt.event,l=h.A,m=s,e=s,n=s,k=s,w=+new Date,p=w,q=0,v=s,x=d("visibilityState"),z=d("hidden"),y;b();(function(){var a=x.replace(/[vV]isibilityState/,"visibilitychange");f.c(document,a,b);f.c(window,"pageshow",b);f.c(window,"pagehide",b);"object"==typeof document.onfocusin?(f.c(document,"focusin",g),f.c(document,"focusout",g)):(f.c(window,"focus",g),
f.c(window,"blur",g))})();h.H={sa:function(){return+new Date-w},qa:function(){return v?+new Date-p+q:q}};l.c("pv-b",function(){f.c(window,"unload",a())});return h.H})();
(function(){var a=mt.lang,b=h.w,d=h.load,g={Da:function(f){if((window._dxt===r||a.d(window._dxt,"Array"))&&"undefined"!==typeof h.b){var g=h.b.J();d([b.protocol,"//datax.baidu.com/x.js?si=",c.id,"&dm=",encodeURIComponent(g)].join(""),f)}},Ma:function(d){if(a.d(d,"String")||a.d(d,"Number"))window._dxt=window._dxt||[],window._dxt.push(["_setUserId",d])}};return h.ha=g})();
(function(){function a(a,d,b,p){if(!(a===r||d===r||p===r)){if(""===a)return[d,b,p].join("*");a=String(a).split("!");for(var q,e=u,f=0;f<a.length;f++)if(q=a[f].split("*"),String(d)===q[0]){q[1]=b;q[2]=p;a[f]=q.join("*");e=s;break}e||a.push([d,b,p].join("*"));return a.join("!")}}function b(a){for(var k in a)if({}.hasOwnProperty.call(a,k)){var e=a[k];d.d(e,"Object")||d.d(e,"Array")?b(e):a[k]=String(e)}}var d=mt.lang,g=mt.l,f=h.w,l=h.A,m=h.ha,e={s:[],D:0,W:u,o:{R:"",page:""},init:function(){e.e=0;l.c("pv-b",
function(){e.ia();e.la()});l.c("pv-d",function(){e.ma();e.o.page=""});l.c("stag-b",function(){h.b.a.api=e.e||e.D?e.e+"_"+e.D:"";h.b.a.ct=[decodeURIComponent(h.b.getData("Hm_ct_"+c.id)||""),e.o.R,e.o.page].join("!")});l.c("stag-d",function(){h.b.a.api=0;e.e=0;e.D=0})},ia:function(){var a=window._hmt||[];if(!a||d.d(a,"Array"))window._hmt={id:c.id,cmd:{},push:function(){for(var a=window._hmt,n=0;n<arguments.length;n++){var p=arguments[n];d.d(p,"Array")&&(a.cmd[a.id].push(p),"_setAccount"===p[0]&&(1<
p.length&&/^[0-9a-f]{32}$/.test(p[1]))&&(p=p[1],a.id=p,a.cmd[p]=a.cmd[p]||[]))}}},window._hmt.cmd[c.id]=[],window._hmt.push.apply(window._hmt,a)},la:function(){var a=window._hmt;if(a&&a.cmd&&a.cmd[c.id])for(var d=a.cmd[c.id],b=/^_track(Event|MobConv|Order|RTEvent)$/,p=0,q=d.length;p<q;p++){var f=d[p];b.test(f[0])?e.s.push(f):e.P(f)}a.cmd[c.id]={push:e.P}},ma:function(){if(0<e.s.length)for(var a=0,d=e.s.length;a<d;a++)e.P(e.s[a]);e.s=t},P:function(a){var b=a[0];if(e.hasOwnProperty(b)&&d.d(e[b],"Function"))e[b](a)},
_setAccount:function(a){1<a.length&&/^[0-9a-f]{32}$/.test(a[1])&&(e.e|=1)},_setAutoPageview:function(a){if(1<a.length&&(a=a[1],u===a||s===a))e.e|=2,h.b.V=a},_trackPageview:function(a){if(1<a.length&&a[1].charAt&&"/"===a[1].charAt(0)){e.e|=4;h.b.a.et=0;h.b.a.ep="";h.b.L?(h.b.a.nv=0,h.b.a.st=4):h.b.L=s;var d=h.b.a.u,b=h.b.a.su;h.b.a.u=f.protocol+"//"+document.location.host+a[1];e.W||(h.b.a.su=document.location.href);h.b.k();h.b.a.u=d;h.b.a.su=b}},_trackEvent:function(a){2<a.length&&(e.e|=8,h.b.a.nv=
0,h.b.a.st=4,h.b.a.et=4,h.b.a.ep=d.j(a[1])+"*"+d.j(a[2])+(a[3]?"*"+d.j(a[3]):"")+(a[4]?"*"+d.j(a[4]):""),h.b.k())},_setCustomVar:function(a){if(!(4>a.length)){var b=a[1],f=a[4]||3;if(0<b&&6>b&&0<f&&4>f){e.D++;for(var p=(h.b.a.cv||"*").split("!"),q=p.length;q<b-1;q++)p.push("*");p[b-1]=f+"*"+d.j(a[2])+"*"+d.j(a[3]);h.b.a.cv=p.join("!");a=h.b.a.cv.replace(/[^1](\*[^!]*){2}/g,"*").replace(/((^|!)\*)+$/g,"");""!==a?h.b.setData("Hm_cv_"+c.id,encodeURIComponent(a),c.age):h.b.Fa("Hm_cv_"+c.id)}}},_setUserTag:function(b){if(!(3>
b.length)){var e=d.j(b[1]);b=d.j(b[2]);if(e!==r&&b!==r){var f=decodeURIComponent(h.b.getData("Hm_ct_"+c.id)||""),f=a(f,e,1,b);h.b.setData("Hm_ct_"+c.id,encodeURIComponent(f),c.age)}}},_setVisitTag:function(b){if(!(3>b.length)){var f=d.j(b[1]);b=d.j(b[2]);if(f!==r&&b!==r){var g=e.o.R,g=a(g,f,2,b);e.o.R=g}}},_setPageTag:function(b){if(!(3>b.length)){var f=d.j(b[1]);b=d.j(b[2]);if(f!==r&&b!==r){var g=e.o.page,g=a(g,f,3,b);e.o.page=g}}},_setReferrerOverride:function(a){1<a.length&&(h.b.a.su=a[1].charAt&&
"/"===a[1].charAt(0)?f.protocol+"//"+window.location.host+a[1]:a[1],e.W=s)},_trackOrder:function(a){a=a[1];d.d(a,"Object")&&(b(a),e.e|=16,h.b.a.nv=0,h.b.a.st=4,h.b.a.et=94,h.b.a.ep=g.stringify(a),h.b.k())},_trackMobConv:function(a){if(a={webim:1,tel:2,map:3,sms:4,callback:5,share:6}[a[1]])e.e|=32,h.b.a.et=93,h.b.a.ep=a,h.b.k()},_trackRTPageview:function(a){a=a[1];d.d(a,"Object")&&(b(a),a=g.stringify(a),512>=encodeURIComponent(a).length&&(e.e|=64,h.b.a.rt=a))},_trackRTEvent:function(a){a=a[1];if(d.d(a,
"Object")){b(a);a=encodeURIComponent(g.stringify(a));var k=function(a){var b=h.b.a.rt;e.e|=128;h.b.a.et=90;h.b.a.rt=a;h.b.k();h.b.a.rt=b},l=a.length;if(900>=l)k.call(this,a);else for(var l=Math.ceil(l/900),p="block|"+Math.round(Math.random()*f.O).toString(16)+"|"+l+"|",q=[],v=0;v<l;v++)q.push(v),q.push(a.substring(900*v,900*v+900)),k.call(this,p+q.join("|")),q=[]}},_setUserId:function(a){a=a[1];m.Da();m.Ma(a)}};e.init();h.ea=e;return h.ea})();
(function(){function a(){"undefined"===typeof window["_bdhm_loaded_"+c.id]&&(window["_bdhm_loaded_"+c.id]=s,this.a={},this.V=s,this.L=u,this.init())}var b=mt.url,d=mt.$,g=mt.F,f=mt.lang,l=mt.cookie,m=mt.i,e=mt.localStorage,n=mt.sessionStorage,k=h.w,w=h.A;a.prototype={M:function(a,b){a="."+a.replace(/:\d+/,"");b="."+b.replace(/:\d+/,"");var d=a.indexOf(b);return-1<d&&d+b.length===a.length},X:function(a,b){a=a.replace(/^https?:\/\//,"");return 0===a.indexOf(b)},B:function(a){for(var d=0;d<c.dm.length;d++)if(-1<
c.dm[d].indexOf("/")){if(this.X(a,c.dm[d]))return s}else{var f=b.U(a);if(f&&this.M(f,c.dm[d]))return s}return u},J:function(){for(var a=document.location.hostname,b=0,d=c.dm.length;b<d;b++)if(this.M(a,c.dm[b]))return c.dm[b].replace(/(:\d+)?[\/\?#].*/,"");return a},T:function(){for(var a=0,b=c.dm.length;a<b;a++){var d=c.dm[a];if(-1<d.indexOf("/")&&this.X(document.location.href,d))return d.replace(/^[^\/]+(\/.*)/,"$1")+"/"}return"/"},ta:function(){if(!document.referrer)return k.m-k.r>c.vdur?1:4;var a=
u;this.B(document.referrer)&&this.B(document.location.href)?a=s:(a=b.U(document.referrer),a=this.M(a||"",document.location.hostname));return a?k.m-k.r>c.vdur?1:4:3},getData:function(a){try{return l.get(a)||n.get(a)||e.get(a)}catch(b){}},setData:function(a,b,d){try{l.set(a,b,{domain:this.J(),path:this.T(),I:d}),d?e.set(a,b,d):n.set(a,b)}catch(f){}},Fa:function(a){try{l.set(a,"",{domain:this.J(),path:this.T(),I:-1}),n.remove(a),e.remove(a)}catch(b){}},Ka:function(){var a,b,d,f,e;k.r=this.getData("Hm_lpvt_"+
c.id)||0;13===k.r.length&&(k.r=Math.round(k.r/1E3));b=this.ta();a=4!==b?1:0;if(d=this.getData("Hm_lvt_"+c.id)){f=d.split(",");for(e=f.length-1;0<=e;e--)13===f[e].length&&(f[e]=""+Math.round(f[e]/1E3));for(;2592E3<k.m-f[0];)f.shift();e=4>f.length?2:3;for(1===a&&f.push(k.m);4<f.length;)f.shift();d=f.join(",");f=f[f.length-1]}else d=k.m,f="",e=1;this.setData("Hm_lvt_"+c.id,d,c.age);this.setData("Hm_lpvt_"+c.id,k.m);d=k.m===this.getData("Hm_lpvt_"+c.id)?"1":"0";if(0===c.nv&&this.B(document.location.href)&&
(""===document.referrer||this.B(document.referrer)))a=0,b=4;this.a.nv=a;this.a.st=b;this.a.cc=d;this.a.lt=f;this.a.lv=e},Ja:function(){for(var a=[],b=this.a.et,d=0,f=k.aa.length;d<f;d++){var e=k.aa[d],g=this.a[e];"undefined"!==typeof g&&""!==g&&("tt"!==e||"tt"===e&&0===b)&&("ct"!==e||"ct"===e&&0===b)&&a.push(e+"="+encodeURIComponent(g))}switch(b){case 0:a.push("sn="+k.S);this.a.rt&&a.push("rt="+encodeURIComponent(this.a.rt));break;case 3:a.push("sn="+k.S);break;case 90:this.a.rt&&a.push("rt="+this.a.rt)}return a.join("&")},
La:function(){this.Ka();this.a.si=c.id;this.a.su=document.referrer;this.a.ds=m.Ga;this.a.cl=m.colorDepth+"-bit";this.a.ln=String(m.language).toLowerCase();this.a.ja=m.javaEnabled?1:0;this.a.ck=m.cookieEnabled?1:0;this.a.lo="number"===typeof _bdhm_top?1:0;this.a.fl=g.ua();this.a.v="1.2.24";this.a.cv=decodeURIComponent(this.getData("Hm_cv_"+c.id)||"");this.a.tt=document.title||"";var a=document.location.href;this.a.cm=b.f(a,k.ya)||"";this.a.cp=b.f(a,k.za)||b.f(a,k.Pa)||"";this.a.cw=b.f(a,k.xa)||b.f(a,
k.Ra)||"";this.a.ci=b.f(a,k.va)||b.f(a,k.Oa)||"";this.a.cf=b.f(a,k.Aa)||b.f(a,k.Qa)||"";this.a.cu=b.f(a,k.wa)||b.f(a,k.Na)||""},init:function(){try{this.La(),0===this.a.nv?this.Ia():this.Q(".*"),h.b=this,this.fa(),w.z("pv-b"),this.Ha()}catch(a){var b=[];b.push("si="+c.id);b.push("n="+encodeURIComponent(a.name));b.push("m="+encodeURIComponent(a.message));b.push("r="+encodeURIComponent(document.referrer));d.log(k.N+"//"+k.Y+"?"+b.join("&"))}},Ha:function(){function a(){w.z("pv-d")}this.V?(this.L=s,
this.a.et=0,this.a.ep="",this.k(a)):a()},k:function(a){var b=this;b.a.rnd=Math.round(Math.random()*k.O);w.z("stag-b");var e=k.N+"//"+k.Y+"?"+b.Ja();w.z("stag-d");b.ca(e);d.log(e,function(d){b.Q(d);f.d(a,"Function")&&a.call(b)})},fa:function(){var a=document.location.hash.substring(1),d=RegExp(c.id),f=-1<document.referrer.indexOf(k.da),e=b.f(a,"jn"),g=/^heatlink$|^select$|^pageclick$/.test(e);a&&(d.test(a)&&f&&g)&&(this.a.rnd=Math.round(Math.random()*k.O),a=document.createElement("script"),a.setAttribute("type",
"text/javascript"),a.setAttribute("charset","utf-8"),a.setAttribute("src",k.protocol+"//"+c.js+e+".js?"+this.a.rnd),e=document.getElementsByTagName("script")[0],e.parentNode.insertBefore(a,e))},ca:function(a){var b=n.get("Hm_unsent_"+c.id)||"",d=this.a.u?"":"&u="+encodeURIComponent(document.location.href),b=encodeURIComponent(a.replace(/^https?:\/\//,"")+d)+(b?","+b:"");n.set("Hm_unsent_"+c.id,b)},Q:function(a){var b=n.get("Hm_unsent_"+c.id)||"";b&&(a=encodeURIComponent(a.replace(/^https?:\/\//,"")),
a=RegExp(a.replace(/([\*\(\)])/g,"\\$1")+"(%26u%3D[^,]*)?,?","g"),(b=b.replace(a,"").replace(/,$/,""))?n.set("Hm_unsent_"+c.id,b):n.remove("Hm_unsent_"+c.id))},Ia:function(){var a=this,b=n.get("Hm_unsent_"+c.id);if(b)for(var b=b.split(","),f=function(b){d.log(k.N+"//"+decodeURIComponent(b),function(b){a.Q(b)})},e=0,g=b.length;e<g;e++)f(b[e])}};return new a})();
(function(){var a=mt.h,b=mt.event,d=mt.url,g=mt.l;try{if(window.performance&&performance.timing&&"undefined"!==typeof h.b){var f=+new Date,l=function(a){var b=performance.timing,d=b[a+"Start"]?b[a+"Start"]:0;a=b[a+"End"]?b[a+"End"]:0;return{start:d,end:a,value:0<a-d?a-d:0}},m=t;a.Z(function(){m=+new Date});var e=function(){var a,b,e;e=l("navigation");b=l("request");e={netAll:b.start-e.start,netDns:l("domainLookup").value,netTcp:l("connect").value,srv:l("response").start-b.start,dom:performance.timing.domInteractive-
performance.timing.fetchStart,loadEvent:l("loadEvent").end-e.start};a=document.referrer;var n=a.match(/^(http[s]?:\/\/)?([^\/]+)(.*)/)||[],v=t;b=t;if("www.baidu.com"===n[2]||"m.baidu.com"===n[2])v=d.f(a,"qid"),b=d.f(a,"click_t");a=v;e.qid=a!=t?a:"";b!=t?(e.bdDom=m?m-b:0,e.bdRun=f-b,e.bdDef=l("navigation").start-b):(e.bdDom=0,e.bdRun=0,e.bdDef=0);h.b.a.et=87;h.b.a.ep=g.stringify(e);h.b.k()};b.c(window,"load",function(){setTimeout(e,500)})}}catch(n){}})();
(function(){var a=mt.i,b=mt.lang,d=mt.event,g=mt.l;if("undefined"!==typeof h.b&&(c.med||(!a.Ea||7<a.Ca)&&c.cvcc)){var f,l,m,e,n=function(a){if(a.item){for(var b=a.length,d=Array(b);b--;)d[b]=a[b];return d}return[].slice.call(a)},k=function(a,b){for(var d in a)if(a.hasOwnProperty(d)&&b.call(a,d,a[d])===u)return u},w=function(a,d){var e={};e.n=f;e.t="clk";e.v=a;if(d){var l=d.getAttribute("href"),k=d.getAttribute("onclick")?""+d.getAttribute("onclick"):t,n=d.getAttribute("id")||"";m.test(l)?(e.sn="mediate",
e.snv=l):b.d(k,"String")&&m.test(k)&&(e.sn="wrap",e.snv=k);e.id=n}h.b.a.et=86;h.b.a.ep=g.stringify(e);h.b.k();for(e=+new Date;400>=+new Date-e;);};if(c.med)l="/zoosnet",f="swt",m=/swt|zixun|call|chat|zoos|business|talk|kefu|openkf|online|\/LR\/Chatpre\.aspx/i,e={click:function(){for(var a=[],b=n(document.getElementsByTagName("a")),b=[].concat.apply(b,n(document.getElementsByTagName("area"))),b=[].concat.apply(b,n(document.getElementsByTagName("img"))),d,e,f=0,g=b.length;f<g;f++)d=b[f],e=d.getAttribute("onclick"),
d=d.getAttribute("href"),(m.test(e)||m.test(d))&&a.push(b[f]);return a}};else if(c.cvcc){l="/other-comm";f="other";m=c.cvcc.q||r;var p=c.cvcc.id||r;e={click:function(){for(var a=[],b=n(document.getElementsByTagName("a")),b=[].concat.apply(b,n(document.getElementsByTagName("area"))),b=[].concat.apply(b,n(document.getElementsByTagName("img"))),d,e,f,g=0,l=b.length;g<l;g++)d=b[g],m!==r?(e=d.getAttribute("onclick"),f=d.getAttribute("href"),p?(d=d.getAttribute("id"),(m.test(e)||m.test(f)||p.test(d))&&
a.push(b[g])):(m.test(e)||m.test(f))&&a.push(b[g])):p!==r&&(d=d.getAttribute("id"),p.test(d)&&a.push(b[g]));return a}}}if("undefined"!==typeof e&&"undefined"!==typeof m){var q;l+=/\/$/.test(l)?"":"/";var v=function(a,d){if(q===d)return w(l+a,d),u;if(b.d(d,"Array")||b.d(d,"NodeList"))for(var e=0,f=d.length;e<f;e++)if(q===d[e])return w(l+a+"/"+(e+1),d[e]),u};d.c(document,"mousedown",function(a){a=a||window.event;q=a.target||a.srcElement;var d={};for(k(e,function(a,e){d[a]=b.d(e,"Function")?e():document.getElementById(e)});q&&
q!==document&&k(d,v)!==u;)q=q.parentNode})}}})();(function(){var a=mt.h,b=mt.lang,d=mt.event,g=mt.l;if("undefined"!==typeof h.b&&b.d(c.cvcf,"Array")&&0<c.cvcf.length){var f={ba:function(){for(var b=c.cvcf.length,g,e=0;e<b;e++)(g=a.oa(decodeURIComponent(c.cvcf[e])))&&d.c(g,"click",f.ka())},ka:function(){return function(){h.b.a.et=86;var a={n:"form",t:"clk"};a.id=this.id;h.b.a.ep=g.stringify(a);h.b.k()}}};a.Z(function(){f.ba()})}})();
(function(){var a=mt.event,b=mt.l;if(c.med&&"undefined"!==typeof h.b){var d=+new Date,g={n:"anti",sb:0,kb:0,clk:0},f=function(){h.b.a.et=86;h.b.a.ep=b.stringify(g);h.b.k()};a.c(document,"click",function(){g.clk++});a.c(document,"keyup",function(){g.kb=1});a.c(window,"scroll",function(){g.sb++});a.c(window,"unload",function(){g.t=+new Date-d;f()});a.c(window,"load",function(){setTimeout(f,5E3)})}})();})();
