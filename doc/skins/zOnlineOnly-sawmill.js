oM=new makeCM("oM"); oM.resizeCheck=1; oM.rows=1;  oM.onlineRoot="/"; oM.pxBetween =0; 
oM.offlineRoot=""; 
oM.fillImg="images/cm_fill.gif"; oM.fromTop=38; oM.fromLeft=155; oM.wait=300; oM.zIndex=400;
oM.useBar=1; oM.barWidth="100%"; oM.barHeight="menu"; oM.barX=0;oM.barY="menu"; oM.barClass="clBar";
oM.barBorderX=0; oM.barBorderY=0;
oM.level[0]=new cm_makeLevel(90,21,"clT","clTover",1,1,"clB",0,"bottom",0,0,0,0,0);
oM.level[1]=new cm_makeLevel(102,22,"clS","clSover",1,1,"clB",0,"right",0,0,"images/menu_arrow.gif",10,9);
oM.level[2]=new cm_makeLevel(110,22,"clS2","clS2over");
oM.level[3]=new cm_makeLevel(140,22);

oM.makeMenu('m100','','Home','http://www.zrato.com/finance/doc/index.htm');
oM.makeMenu('m200','','Doc','');
oM.makeMenu('m300','','Download','http://www.zrato.com/finance/doc/download.htm');
oM.makeMenu('m400','','JavaDoc','http://www.zrato.com/finance/doc/javadoc/overview-summary.html');
oM.makeMenu('m500','','About Us','');
oM.makeMenu('m210','m200','Database','http://www.zrato.com/finance/doc/database.htm');
oM.makeMenu('m220','m200','Install','http://www.zrato.com/finance/doc/installwindows.htm');
oM.makeMenu('m230','m200','iText','http://www.zrato.com/finance/doc/itext.htm');
oM.makeMenu('m240','m200','License','http://www.zrato.com/finance/doc/license.htm');
oM.makeMenu('m250','m200','Quick','http://www.zrato.com/finance/doc/quicktutorial.htm');
oM.makeMenu('m260','m200','Tutorial','http://www.zrato.com/finance/doc/tutorial.htm');
oM.makeMenu('m510','m500','Who We Are','http://www.zrato.com/finance/doc/aboutus.htm');
oM.makeMenu('m520','m500','Contact Us','http://www.zrato.com/finance/doc/contactus.htm');

var avail="20+((cmpage.x2-235)/5)";
oM.menuPlacement=new Array(22,avail+"-4",avail+"*2-2",avail+"*3+26",avail+"*4+36")
oM.construct()