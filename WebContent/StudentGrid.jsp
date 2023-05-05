<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- 	<%@page import="com.bean.StudentBean" %> --%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="assets/jquery-ui-1.12.1/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="assets/jquery.jqGrid-4.4.3/css/ui.jqgrid.css" />
	

<script type="text/javascript"
	src="assets/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
<script type="text/javascript"
	src="assets/jquery/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript"
	src="assets/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="assets/jquery.jqGrid-4.4.3/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="assets/jquery.jqGrid-4.4.3/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="assets/jquery.jqGrid-4.4.3/js/jquery.jqGrid.src.js"></script>
<title>Insert title here</title>


<script type="text/javascript">
		jQuery(document).ready(function() {
			
//Add ModelView
			$("#AddStudentDiv").dialog({
				autoOpen:false,
				 height: 300,
				  width: 450,
				modal:true,
				title: "Adding New Student",
				buttons:{
					"Cancel": function(){
						$(this).dialog('close');
						},				
					"Add":function(){
							modelAddStudent();
							$(this).dialog('close');	
							$('#list').trigger("reloadGrid");
						},

					},
					
				});
//Edit Model View
			$("#EditStudentDiv").dialog({
				autoOpen:false,
				 height: 300,
				  width: 450,
				modal:true,
				title: "Edit Student Record",
				buttons:{
					"Cancel": function(){
						$(this).dialog('close');
						},
						"Edit":function(){
							modelEditStudent();
							$(this).dialog('close');
							$('#list').trigger("reloadGrid");
						},
				},
			});
//Delete model view
			$("#DelStudentDiv").dialog({
				autoOpen:false,
				 height: 200,
				  width: 250,
				modal:true,
				title: "Delete Student Record",
				buttons:{
					"Cancel": function(){
						$(this).dialog('close');
						},
						"Del":function(){
							modelDelStudent();
							$(this).dialog('close');
							$('#list').trigger("reloadGrid");
						},
						 
				},
				
			});

			//Main Grid
			jQuery("#list").jqGrid({
                 mtype : "POST",
                 datatype : "JSON",
				 url : "ViewServlet",
				 autowidth:true,
				// editurl: 'ViewServlet',
                 colNames : ['Action','Name','Maths','Physics','Chemistry','English'],
	             colModel : [ { name:'action', width :15 ,formatter:function(cellvalue, options, rowObject, action){
		 	            	var actionButton = "";
		 	            	actionButton = "<input type='button' id = 'addButttonInGrid' value='ADD' name='Add' onClick='callAddFunctionModel()'  /> <input type='button' id = 'editButttonInGrid' value='EDIT' name='EDIT' onClick='callEditFunctionModel(\""+rowObject.name+"\",\""+rowObject.maths+"\",\""+rowObject.physics+"\",\""+rowObject.chemistry+"\",\""+rowObject.english+"\")'  /> <input type='button' id = 'delButttonInGrid' value='DEL' name='del' onClick='callDelFunctionModel(\""+rowObject.name+"\")'  />";
		 	            	return actionButton;
		 	            	     	}},
 	            	
                          { name : 'name', index : 'name', width : 30,editable :true,search:true,key:true},
                          {name : 'maths',  index : 'maths',width : 30,editable : true,search:false}, 
                          { name : 'physics', index : 'physics',  width : 30, editable : true,search:false},
                          {name : 'chemistry', index : 'chemistry',width : 30, editable : true ,search:false}, 
                          { name : 'english',index : 'english', width : 30,editable : true,search:false}, 
                          ],
             pager : '#pager',
             rowNum : 10,
             rowList : [ 10, 30, 150 ],
             sortname : 'invid',
             sortorder : 'desc',
             viewrecords : true,
             gridview : true,
             subGrid:true,
             height:235,
             caption : 'Students Record',
             //loadonce: true, //loadonce load page only one time so selected nos of data will be shown
           //  refresh:true,  //loadonce and refresh button doesn't work simultaneously, as both operate differently

         
             //changing the colour of row data according to the parameters using load complete function
               loadComplete: function(data) {      
            	  	    for(var i=0; i<data.rows.length; i++){
        	    	    	var name = data.rows[i].name;
        	    	    	if(name=="ROCKY")
            	    	    	{
        	    	        	    	    	
        	    	                        var newname=i+1;
        	    	                        jQuery("#list").jqGrid('setCell',newname,"name","",{'background-color':'#FF0000'});



            	  	    }
        	    	    }
                },
               
            
             //changing the colour of row on single select
             onSelectRow : function(id) {
            	// $('#list').jqGrid('setRowData', id, true, "#0000FF");
            	 $(this).find('.ui-state-highlight').css({background:'#80BFFF'});
             },
           
            	
             //calling second grid on double clicking the row
    

            	    ondblClickRow:function(rowid)
                    {
            	    	
            	    	jQuery("#secondlist").jqGrid({
                            mtype : "POST",
                            datatype : "JSON",  						
    						url : 'SubGridServlet?name='+rowid,  
    						mtype : "POST",
    						datatype:"JSON",
    						autowidth:true,
    						height:25,
    						//width:400,
    						 colNames : [ 'Name','Maths','Physics','Chemistry','English'],
    			             colModel : [ { name : 'name', index : 'name', width : 30,editable :true,key:true},
    			                          {name : 'maths',  index : 'maths',width : 30,editable : true}, 
    			                          { name : 'physics', index : 'physics',  width : 30, editable : true},
    			                          {name : 'chemistry', index : 'chemistry',width : 30, editable : true }, 
    			                          { name : 'english',index : 'english', width : 30,editable : true}, 
    			                          ],
    			                          rowNum:10,
    			                            jsonReader: {
                            					root: 'data',
                            					repeatitems: false
                        			},
    			                          
    			                          
    						});
    					
                    
                    },
              	
                 
              
             

		          jsonReader : {
					root : "rows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					Id : "0"
				}, 
			autowidth : true,

			//**************************************************************************//
				//function containing two parameters grid row and the primary key
 				subGridRowExpanded : function(subgridid,rowid){
					//create unique tabe and pager id
					var subgrid_table_id=subgridid+"_t";  //parent row id and _t
					var pagerId="pager"+subgrid_table_id; 
					var myGrid = $('#myGridId');
					//send the parent row primary key to the server so that we know which grid to show 
				var childgridURL = "name ="+rowid;
					// selectedRowData = myGrid.getRowData(selectedRowIds[selectedRowIndex]);

					
					$("#"+subgridid).html("<table id='"+subgrid_table_id+ "' style='width:100%'></table><div id='" + pagerId +"'class='scroll'></div>");
					jQuery("#"+subgrid_table_id).jqGrid({
						height:25,
						width:350,
						
						url : 'SubGridServlet?name='+rowid,  
						
						mtype : "POST",
						datatype:"JSON",
						 colNames : [ 'Name','Maths','Physics','Chemistry','English'],
			             colModel : [ { name : 'name', index : 'name', width : 30,editable :true,key:true},
			                          {name : 'maths',  index : 'maths',width : 30,editable : true}, 
			                          { name : 'physics', index : 'physics',  width : 30, editable : true},
			                          {name : 'chemistry', index : 'chemistry',width : 30, editable : true }, 
			                          { name : 'english',index : 'english', width : 30,editable : true}, 
			                          ],
			                          rowNum:10,
			                            jsonReader: {
                        					root: 'data',
                        					repeatitems: false
                    			},
			                          
			                          
						});
					}		

			});
		
			
			
			jQuery("#list").jqGrid('navGrid', '#pager', 
					{
             edit : true,
             add : false,
             del : true,
             search : true,
             view:true,
             position:"left",
             refresh:"true"         
			},{},{},{},
			{sopt:['cn','eq'],closeOnEscape:true,searchOnEnter:true },{},
			
			//options for the Edit Dialog
			{
				editCaption : "The Edit Dialog",
				recreateForm : true,
				closeAfterEdit : true,
				onclickSubmit : function(response, postdata) {
				
					edit();
				}
			},
			
			// options for the Add Dialog
			{
				addCaption :"The Add Dialog",
				closeAfterAdd : false,
				recreateForm : true,
				onclickSubmit : function(response, postdata) {

					add();

				},
				errorTextFormat : function(data) {
					return 'Error: ' + data.responseText
				}
			},
			
			//option for the Delete Dialog
			{
				onclickSubmit : function(response,postdata){
					del();
					},
			},

			
			);
		});
		
		
		function edit(){
				var name=$("#name").val();
				var maths=$("#maths").val();
				var physics=$("#physics").val();
				var chemistry=$("#chemistry").val();
				var english=$("#english").val();
			$.ajax({
				type : "POST",
				url : "EditServlet",
				data : {
					name : name,
					maths : maths,
					physics : physics,
					chemistry : chemistry,
					english : english,
					},
					success : function(gridmodel) {
						alert("Successfully updated");
						},
						error : function(gridmodel) {
						alert("FAIL");
						}
				});
			}
		//function to edit student by model view
		function modelEditStudent(){
				var name=$("#eName").val();
				var maths=$("#eMaths").val();
				var physics=$("#ePhysics").val();
				var chemistry=$("#eChemistry").val();
				var english=$("#eEnglish").val();
			$.ajax({
				type : "POST",
				url : "EditServlet",
				data : {
					name : name,
					maths : maths,
					physics : physics,
					chemistry : chemistry,
					english : english,
					},
					success : function(gridmodel) {
						$('#list').setGridParam({
	        				url: "ViewServlet",
	        			}).trigger("reloadGrid");
						alert("Successfully updated");
						
						},
						error : function(gridmodel) {
						alert("FAIL");
						}
				});
			}
		//function to add student by model view
		function modelAddStudent(){
			var name=$("#mName").val();
			var maths=$("#mMaths").val();
			var physics=$("#mPhysics").val();
			var chemistry=$("#mChemistry").val();
			var english=$("#mEnglish").val();
			$.ajax({
				type : "POST",
				url :"AddServlet",
				data : {
					name : name,
					maths : maths,
					physics : physics,
					chemistry : chemistry,
					english : english,
					},
					success : function(gridmodel){
						$('#list').setGridParam({
	        				url: "ViewServlet",
	        			}).trigger("reload");
						alert("Data is Inserted Successfull");
						$("#list").trigger("reloadGrid");
						
						},
					error:function(gridmodel){
						alert("Data is NOT INSERTED");
						}

				});
			}
		
		function add(){
			var name=$("#name").val();
			var maths=$("#maths").val();
			var physics=$("#physics").val();
			var chemistry=$("#chemistry").val();
			var english=$("#english").val();
			$.ajax({
				type : "POST",
				url :"AddServlet",
				data : {
					name : name,
					maths : maths,
					physics : physics,
					chemistry : chemistry,
					english : english,
					},
					success : function(gridmodel){
						alert("Data is Inserted Successfull");
						},
					error:function(gridmodel){
						alert("Data is NOT INSERTED");
						}

				});
		}

		//function to add student by model view
		function modelDelStudent(){
				var name=$("#dName").val();
			$.ajax({
				type :"POST",
				url :"DeleteServlet",
				data :{
					name: name,
					},
					success :function(gridmodel){
						$('#list').setGridParam({
	        				url: "ViewServlet",
	        			}).trigger("reloadGrid");
						alert("Deletion Successfull");
						},
						error:function(gridmodel){
							alert("Deletion Unsuccessful");
							}
					
				});
		
			}
		function del(){
			var rowId = $("#list").jqGrid('getGridParam', 'selrow');
			var rowData = jQuery("#list").getRowData(rowId);
			if (rowData != null)
				var name=rowData.name;
			$.ajax({
				type :"POST",
				url :"DeleteServlet",
				data :{
					name: name,
					},
					success :function(gridmodel){
						alert("Deletion Successfull");
						},
						error:function(gridmodel){
							alert("Deletion Unsuccessful");
							}
					
				});
		
			}
		function callAddFunctionModel(){
			$("#AddStudentDiv").dialog("open");		
		}
		function callEditFunctionModel(name,maths,physics,chemistry,english){
			$("#EditStudentDiv").dialog("open");
			$("#eName").val(name);
			$("#eMaths").val(maths);
			$("#ePhysics").val(physics);
			$("#eChemistry").val(chemistry);
			$("#eEnglish").val(english);
			
			}
		function callDelFunctionModel(name){
			$("#DelStudentDiv").dialog("open");
			$("#dName").val(name);
			}
		
	</script>
</head>
<body>
	<table id="list" class="ui-jqgrid-btable" style="width: 100%;">
	</table>
	<table id="secondlist" class="ui-jqgrid-btable" style="width: 100%;margin-left:auto;margin-right:auto;">
	</table>
	<div id="pager"></div>
    <%--   <h1>Displaying Student List</h1>
      <table border ="1" width="500" align="center">
         <tr bgcolor="00FF7F">
          <th><b>Name</b></th>
          <th><b>Maths</b></th>
          <th><b>Physics</b></th>
          <th><b>Chemistry</b></th>
          <th><b>English</b></th>
          
         </tr>
        Fetching the attributes of the request object
             which was previously set by the servlet 
              "StudentServlet.java"
        
     
        <% ArrayList<StudentBean> l1 =(ArrayList<StudentBean>)request.getAttribute("searchdata");%>
        <%for(StudentBean s:l1) { %>
       
            <tr>
                <td><%=s.getName() %></td>
                <td><%=s.getMaths() %></td>
                <td><%=s.getPhysics() %></td>
                <td><%=s.getChemistry() %></td>
                <td><%=s.getEnglish() %></td>
            </tr>
            <%}%>
        </table> 
        <hr/> --%>
        <%-- following code to add student by model view --%>
        <div id="AddStudentDiv" style="display:none;">
        	<center>
        	<div style="width:100%;">
			<form>
			<table>
				<tbody>
				<tr>
					<td>Enter Name:</td>
					<td><input type="text" id="mName"/></td>
				</tr>
				<tr>
					<td>Maths:</td>
					<td><input type="text" id="mMaths"/></td>
				</tr>
				<tr>
					<td>Physics:</td>
					<td><input type="text" id="mPhysics"/></td>
				</tr>
				<tr>
					<td>Chemistry:</td>
					<td><input type="text" id="mChemistry"/></td>
				</tr>
				<tr>
					<td>English:</td>
					<td><input type="text" id="mEnglish"/></td>
				</tr>
				</tbody>
			</table>
			</form>        	
        	</div>
        	</center>
        </div>
        
         <%-- following code to edit student by model view --%>
        <div id="EditStudentDiv" style="display:none;">
        	<center>
        	<div style="width:100%;">
			<form>
			<table>
				<tbody>
				<tr>
					<td>Enter Name:</td>
					<td><input type="text" id="eName"/></td>
				</tr>
				<tr>
					<td>Maths:</td>
					<td><input type="text" id="eMaths"/></td>
				</tr>
				<tr>
					<td>Physics:</td>
					<td><input type="text" id="ePhysics"/></td>
				</tr>
				<tr>
					<td>Chemistry:</td>
					<td><input type="text" id="eChemistry"/></td>
				</tr>
				<tr>
					<td>English:</td>
					<td><input type="text" id="eEnglish"/></td>
				</tr>
				</tbody>
			</table>
			</form>        	
        	</div>
        	</center>
        </div>
        
          <%-- following code to delete student by model view --%>
        <div id="DelStudentDiv" style="display:none;">Are you sure you want to delete ?
        	<center>
        	<div style="width:100%;">
			<form>
			<table>
				<tbody>
				<tr>
					<td><input type="hidden" id="dName"/></td>
				</tr>
				</tbody>
			</table>
			</form>        	
        	</div>
        	</center>
        </div>
    </body>
</html>