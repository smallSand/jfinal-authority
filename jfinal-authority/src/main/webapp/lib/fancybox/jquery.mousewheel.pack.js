!function(e){"function"==typeof define&&define.amd?define(["jquery"],e):"object"==typeof exports?module.exports=e:e(jQuery)}(function(e){function t(t){var l=t||window.event,i=[].slice.call(arguments,1),s=0,h=0,a=0,u=0,u=0;t=e.event.fix(l),t.type="mousewheel",l.wheelDelta&&(s=l.wheelDelta),l.detail&&(s=-1*l.detail),l.deltaY&&(s=a=-1*l.deltaY),l.deltaX&&(h=l.deltaX,s=-1*h),void 0!==l.wheelDeltaY&&(a=l.wheelDeltaY),void 0!==l.wheelDeltaX&&(h=-1*l.wheelDeltaX),u=Math.abs(s),(!n||n>u)&&(n=u),u=Math.max(Math.abs(a),Math.abs(h)),(!o||o>u)&&(o=u),l=s>0?"floor":"ceil",s=Math[l](s/n),h=Math[l](h/o),a=Math[l](a/o);try{t.originalEvent.hasOwnProperty("wheelDelta")}catch(r){a=s}return i.unshift(t,s,h,a),(e.event.dispatch||e.event.handle).apply(this,i)}var n,o,l=["wheel","mousewheel","DOMMouseScroll","MozMousePixelScroll"],i="onwheel"in document||9<=document.documentMode?["wheel"]:["mousewheel","DomMouseScroll","MozMousePixelScroll"];if(e.event.fixHooks)for(var s=l.length;s;)e.event.fixHooks[l[--s]]=e.event.mouseHooks;e.event.special.mousewheel={setup:function(){if(this.addEventListener)for(var e=i.length;e;)this.addEventListener(i[--e],t,!1);else this.onmousewheel=t},teardown:function(){if(this.removeEventListener)for(var e=i.length;e;)this.removeEventListener(i[--e],t,!1);else this.onmousewheel=null}},e.fn.extend({mousewheel:function(e){return e?this.bind("mousewheel",e):this.trigger("mousewheel")},unmousewheel:function(e){return this.unbind("mousewheel",e)}})});