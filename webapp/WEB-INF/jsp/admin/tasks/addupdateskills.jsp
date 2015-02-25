<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/commontaglibs.jspf"%>
<%@include file="../../common/environment.jspf"%>

<!-- Add new skill panel -->
<c:choose>
	<c:when test="${update}"><c:set var="method" value="updateSkill"/></c:when>
	<c:otherwise><c:set var="method" value="saveSkills"/></c:otherwise>
</c:choose>		
<div class="modal-content addNewSkill-modal">
	<form role="form" action="skillset.do?method=${method}&selection=addskills" method="post">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">
				<c:choose>
					<c:when test="${update}">Update Skill</c:when>
					<c:otherwise>Add New Skill</c:otherwise>
				</c:choose>				
			</h4>
		</div>
		<div class="modal-body">
			<p>Skill Name</p>
			<c:if test="${update}">
				<%-- hidden field to set the id. This is needed only for update --%>
				<input type="hidden" name="id" value="${skill.id}"/>
			</c:if>
			<input type="text" name="name" placeholder="Skill name" autocomplete="off" class="form-control placeholder-no-fix" value="${skill.name}">
			<p>Category</p>
			<select  class="form-control placeholder-no-fix" name="category" >
				<c:forEach items="${categories}" var="category">
					<option <c:if test="${skill.category eq category}">selected="selected"</c:if> value="${category}">${category}</option>					
				</c:forEach>	
			</select>								
			<p>Skill Description</p>								
			 <textarea class="form-control placeholder-no-fix" rows="6" placeholder="Enter description" name="description">${skill.description}</textarea>	
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">
				<c:choose>
					<c:when test="${update}">Update Skill</c:when>
					<c:otherwise>Save Skill</c:otherwise>
				</c:choose>	
			</button>
		</div>
	</form>
</div>
