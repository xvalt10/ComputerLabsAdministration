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

  $("#classroomMessages").hide();
    $("#addClassroomForm").show();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").hide();
}
function showHardwareTab() {
   activateTab("#hardwareTab");
   
   $("#hardwareMessages").hide();
    
    $("#addClassroomForm").hide();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").show();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").hide();

}

function showSoftwareTab(){
    
    $("#softwareMessages").hide();
    activateTab("#softwareTab");
      $("#addClassroomForm").hide();
    $("#addSoftwareForm").show();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").hide();
    
}

function showSoftwaretoHardwareTab(){
    
    $("#swhwMessages").hide();
    activateTab("#addSWtoHWTab");
      $("#addClassroomForm").hide();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").show();
    $("#overviewForm").hide();
    
}

function showClassroomTab2() {
    
    activateTab("#classroomTab");
    $("#classroomMessages").show();
    $("#addClassroomForm").show();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").hide();
}
function showHardwareTab2() {
   activateTab("#hardwareTab");
   
   $("#hardwareMessages").show();
    $("#addClassroomForm").hide();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").show();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").hide();

}

function showSoftwareTab2(){
    
    activateTab("#softwareTab");
    $("#softwareMessages").show();
      $("#addClassroomForm").hide();
    $("#addSoftwareForm").show();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").hide();
    
}

function showSoftwaretoHardwareTab2(){
    
    activateTab("#addSWtoHWTab");
    $("#swhwMessages").show();
      $("#addClassroomForm").hide();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").show();
    $("#overviewForm").hide();
    
}


function showResourcesOverview2(){
     activateTab("#overviewTab");
    
      $("#addClassroomForm").hide();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").show();
   
}

function showResourcesOverview(){
     activateTab("#overviewTab");
    
      $("#addClassroomForm").hide();
    $("#addSoftwareForm").hide();
    $("#addHardwareForm").hide();
    $("#addSWtoHWForm").hide();
    $("#overviewForm").show();
   
}

function showComplaintPanel(){
    activateTab("#complaintTab");
      $("#complaintPanel").show();
    $("#solutionPanel").hide();
    
}
function showSolutionPanel(){
    
    activateTab("#solutionTab");
      $("#complaintPanel").hide();
    $("#solutionPanel").show();
    
}

function loadComplaintTabs(){
    
     $("#complaintTab").on("click", showComplaintPanel);
    $("#solutionTab").on("click", showSolutionPanel);
}

function loadTabs() {
    
  
     
    switch(get('tab')) {
       
    case 'hardware':showHardwareTab2();
        break;
    case 'software':showSoftwareTab2();  
        break;
    case 'swTohw':showSoftwaretoHardwareTab2(); 
        break;
    case 'overview':showResourcesOverview2();
    break;
    default: showClassroomTab2();
        break;
       
}

    $("#classroomTab").on("click", showClassroomTab);
    $("#hardwareTab").on("click", showHardwareTab);
    $("#softwareTab").on("click", showSoftwareTab );
    $("#addSWtoHWTab").on("click", showSoftwaretoHardwareTab);
    $("#overviewTab").on("click", showResourcesOverview);
        
}
