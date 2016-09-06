function $(id){
	return document.getElementById(id);
}
function getByClass(oParent,sClass)
{
    var aEle = oParent.getElementsByTagName('*');
    var aResult = [];
    var re=new RegExp('\\b'+sClass+'\\b', 'i');
    
    for(var i=0; i<aEle.length;i++)
    {
        if(aEle[i].className.search(re)!=-1)
        {
            aResult.push(aEle[i]);
        }
    }
    return aResult;
}
window.onload = function(){
	//导航
	var btn_munu = $('btn_menu');
	var header_nav = $('header_nav');
	var header_nav_false = true;
	
	btn_munu.onclick = function(){
		if(header_nav_false){
			header_nav.style.height = header_nav.scrollHeight + 'px';
			header_nav_false = false;
		}
		else{
			header_nav.style.height = 0;
			header_nav_false = true;
		}
	}
	//内容
	var cont = $('cont');
	var iNow = 0;
	if(cont){
		var aItem = getByClass(cont,'item');
		for(var i=0;i<aItem.length;i++){
			aItem[i].getElementsByTagName('h3')[0].index = i;
			aItem[i].getElementsByTagName('h3')[0].bFalse = true;
			aItem[i].getElementsByTagName('h3')[0].onclick = function(){
				iNow = this.index;
				if(this.bFalse){
					this.className = 'active';
					aItem[iNow].getElementsByTagName('ul')[0].style.display = 'none';
					this.bFalse = false;
				}
				else{
					this.className = '';
					aItem[iNow].getElementsByTagName('ul')[0].style.display = 'block';
					this.bFalse = true;
				}
			}
		}
	}
}