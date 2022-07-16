var mousedown = false; 
var td = ""; 
var td_width; 
var x = 0; 

function TCstartColResize(obj){
mousedown = true;
td = obj;
td_width = td.width;
x = event.clientX;
}
function TCColResize()
{

if (mousedown){
var distX = event.x - x; 
td.width = parseInt(td_width) + parseInt(distX);
}
}
function TCstopColResize(){
mousedown = false;
td = '';
}

function cell_left(obj){
if(event.offsetX < 5 && obj.cellIndex!=0)
return true;
else 
return false;
}
function cell_right(obj){
if(event.offsetX > obj.width-4)
return true;
else 
return false;
}


document.onmousedown = function(){

try{
var now_mousedown = window.event.srcElement;
if(now_mousedown.className.toUpperCase()=="COLRESIZE"){
if( cell_left(now_mousedown) ){
now_mousedown = now_mousedown.parentNode.childNodes[now_mousedown.cellIndex-1];
}else if( !cell_right(now_mousedown) ){
return true;
}
TCstartColResize(now_mousedown);
}
}catch(e){ return true; }
}


document.onmousemove = function(){
try{
var now_mousemove = window.event.srcElement;
if(now_mousemove.className.toUpperCase()=="COLRESIZE" || td!=""){


if( cell_left(now_mousemove) || cell_right(now_mousemove) ){
now_mousemove.style.cursor = "col-resize";
}else{
now_mousemove.style.cursor = "";
}

TCColResize(now_mousemove);
}else{
now_mousemove.style.cursor = "";
}
}catch(e){ return true; }
}


document.onmouseup = function(){
try{
var now_mouseup = window.event.srcElement;
//if(now_mouseup.className=="colResize"){
TCstopColResize(now_mouseup);
//}
}catch(e){ return true; }
}


document.onselectstart = function(){
try{
if(td != ""){
return false;
}
}catch(e){ return true; }
}