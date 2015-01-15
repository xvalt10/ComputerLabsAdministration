/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 function loadTabs(){
     $("#addClassroomForm").show();
     $("#addSoftwareForm").hide();
     $("#addHardwareForm").hide();
     
     $("#classroomTab").on("click",function(){
         $("#addClassroomForm").show();
         $("#addSoftwareForm").hide();
         $("#addHardwareForm").hide();
     });
     
     $("#hardwareTab").on("click",function(){
         $("#addClassroomForm").hide();
         $("#addSoftwareForm").hide();
         $("#addHardwareForm").show();
     });
     
     $("#softwareTab").on("click",function(){
         $("#addClassroomForm").hide();
         $("#addSoftwareForm").show();
         $("#addHardwareForm").hide();
     });
 }
