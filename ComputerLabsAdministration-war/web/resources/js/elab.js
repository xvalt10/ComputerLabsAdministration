/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function get(name) {
    if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
        return decodeURIComponent(name[1]);
}

function activateTab(tabId){
    
    $("#tabsList").find("li").removeClass("active");
    $(tabId).addClass("active");
    
}

function showClassroomTab() {
    activateTab("#classroomTab");
  
    $("#addClassroomForm").show();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").hide();
}
function showHardwareTab() {
   activateTab("#hardwareTab");
    
    $("#addClassroomForm").hide();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").show();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").hide();

}

function showSoftwareTab(){
    activateTab("#softwareTab");
      $("#addClassroomForm").hide();
    $("#addSoftwareForm").show();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").hide();
    
}

function showSoftwaretoHardwareTab(){
    activateTab("#addSWtoHWTab");
      $("#addClassroomForm").hide();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").show();
    $("#overviewForm").hide();
    
}

function showResourcesOverview(){
     activateTab("#overviewTab");
    
      $("#addClassroomForm").hide();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").show();
   
}



function loadTabs() {
    
   
    
    switch(get('tab')) {
       
    case 'hardware':showHardwareTab();
        break;
    case 'software':showSoftwareTab();  
        break;
    case 'swTohw':showSoftwaretoHardwareTab(); 
        break;
    case 'overview':showResourcesOverview();
    break;
    default: showClassroomTab();
        break;
       
}

    $("#classroomTab").on("click", showClassroomTab);
    $("#hardwareTab").on("click", showHardwareTab);
    $("#softwareTab").on("click", showSoftwareTab );
    $("#addSWtoHWTab").on("click", showSoftwaretoHardwareTab);
    $("#overviewTab").on("click", showResourcesOverview);
}
