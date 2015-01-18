/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 function loadTabs(){
     
     $("#addClassroomForm").show();
     $("#addSoftwareForm").hide();
     $("#addHardwareForm").hide();
     $("#addSWtoHWForm").hide();
     $("#overviewForm").hide();
     
     
     
     
     $("#classroomTab").on("click",function(){
         $("#addClassroomForm").show();
         $("#addSoftwareForm").hide();
         $("#addHardwareForm").hide();
           $("#addSWtoHWForm").hide();
     $("#overviewForm").hide();
     });
     
     $("#hardwareTab").on("click",function(){
         $("#addClassroomForm").hide();
         $("#addSoftwareForm").hide();
         $("#addHardwareForm").show();
           $("#addSWtoHWForm").hide();
     $("#overviewForm").hide();
     });
     
     $("#softwareTab").on("click",function(){
         $("#addClassroomForm").hide();
         $("#addSoftwareForm").show();
         $("#addHardwareForm").hide();
           $("#addSWtoHWForm").hide();
     $("#overviewForm").hide();
     });
     
      $("#addSWtoHWTab").on("click",function(){
         $("#addClassroomForm").hide();
         $("#addSoftwareForm").hide();
         $("#addHardwareForm").hide();
           $("#addSWtoHWForm").show();
     $("#overviewForm").hide();
     });
     
      $("#overviewTab").on("click",function(){
         $("#addClassroomForm").hide();
         $("#addSoftwareForm").hide();
         $("#addHardwareForm").hide();
           $("#addSWtoHWForm").hide();
     $("#overviewForm").show();
     });
 }
