!function(e){var n={};function t(r){if(n[r])return n[r].exports;var i=n[r]={i:r,l:!1,exports:{}};return e[r].call(i.exports,i,i.exports,t),i.l=!0,i.exports}t.m=e,t.c=n,t.d=function(e,n,r){t.o(e,n)||Object.defineProperty(e,n,{enumerable:!0,get:r})},t.r=function(e){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},t.t=function(e,n){if(1&n&&(e=t(e)),8&n)return e;if(4&n&&"object"==typeof e&&e&&e.__esModule)return e;var r=Object.create(null);if(t.r(r),Object.defineProperty(r,"default",{enumerable:!0,value:e}),2&n&&"string"!=typeof e)for(var i in e)t.d(r,i,function(n){return e[n]}.bind(null,i));return r},t.n=function(e){var n=e&&e.__esModule?function(){return e.default}:function(){return e};return t.d(n,"a",n),n},t.o=function(e,n){return Object.prototype.hasOwnProperty.call(e,n)},t.p="/",t(t.s=9)}([function(e,n){e.exports=require("react/jsx-runtime")},function(e,n){e.exports=require("react-router-dom")},function(e,n){e.exports=require("react")},function(e,n,t){e.exports=t(8)},function(e,n){e.exports=require("express")},function(e,n){e.exports=require("path")},function(e,n){e.exports=require("react-dom/server")},function(e,n){e.exports=require("fs")},function(e,n){e.exports=require("regenerator-runtime")},function(e,n,t){"use strict";t.r(n);var r=t(3),i=t.n(r);function o(e,n,t,r,i,o,c){try{var u=e[o](c),s=u.value}catch(e){return void t(e)}u.done?n(s):Promise.resolve(s).then(r,i)}t(2);var c=t(6),u=t.n(c),s=t(4),a=t.n(s),l=t(1),f=t(0),d=function(){return Object(f.jsxs)("ul",{children:[Object(f.jsx)("li",{children:Object(f.jsx)(l.Link,{to:"/red",children:"Red"})}),Object(f.jsx)("li",{children:Object(f.jsx)(l.Link,{to:"/blue",children:"Blue"})})]})},p=function(){return Object(f.jsx)("div",{className:"Red",children:"Red"})},j=function(){return Object(f.jsx)(p,{})},h=function(){return Object(f.jsx)("div",{className:"Blue",children:"Blue"})},b=function(){return Object(f.jsx)(h,{})};var x=function(){return Object(f.jsxs)("div",{children:[Object(f.jsx)(d,{}),Object(f.jsx)("hr",{}),Object(f.jsx)(l.Route,{path:"/red",component:j}),Object(f.jsx)(l.Route,{path:"/blue",component:b})]})},v=t(5),m=t.n(v),O=t(7),y=t.n(O),g=JSON.parse(y.a.readFileSync(m.a.resolve("./build/asset-manifest.json"),"utf8")),S=Object.keys(g.files).filter((function(e){return/chunk\.js$/.exec(e)})).map((function(e){return'<script src="'.concat(g.files[e],'"><\/script>')})).join("");function k(e,n){return'<!DOCTYPE html>\n    <html lang="en">\n    <head>\n      <meta charset="utf-8" />\n      <link rel="shortcut icon" href="/favicon.ico" />\n      <meta\n        name="viewport"\n        content="width=device-width,initial-scale=1,shrink-to-fit=no"\n      />\n      <meta name="theme-color" content="#000000" />\n      <title>React App</title>\n      <link href="'.concat(g.files["main.css"],'" rel="stylesheet" />\n    </head>\n    <body>\n      <noscript>You need to enable JavaScript to run this app.</noscript>\n      <div id="root">\n        ').concat(e,'\n      </div>\n      <script src="').concat(g.files["runtime-main.js"],'"><\/script>\n      ').concat(S,'\n      <script src="').concat(g.files["main.js"],'"><\/script>\n    </body>\n    </html>\n      ')}var q=a()(),w=function(){var e,n=(e=i.a.mark((function e(n,t,r){var o,c,s;return i.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:o={},c=Object(f.jsx)(l.StaticRouter,{location:n.url,context:o,children:Object(f.jsx)(x,{})}),s=u.a.renderToString(c),t.send(k(s));case 4:case"end":return e.stop()}}),e)})),function(){var n=this,t=arguments;return new Promise((function(r,i){var c=e.apply(n,t);function u(e){o(c,r,i,u,s,"next",e)}function s(e){o(c,r,i,u,s,"throw",e)}u(void 0)}))});return function(e,t,r){return n.apply(this,arguments)}}(),P=a.a.static(m.a.resolve("./build"),{index:!1});q.use(P),q.use(w),q.listen(5e3,(function(){console.log("Running on http://localhost:5000")}))}]);