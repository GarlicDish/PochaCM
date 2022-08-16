<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- get the String List to compare with keyword --%>
<%
	List<Category> ictList = (List) request.getAttribute("ictList");
%>

<% for (int i = 0; i < ictList.size(); i++) { %>
<div ><span  value="<%=ictList.get(i).getItemCateNum()%>"><%=ictList.get(i).getItemCateName()%></span></div>
<% } %>
<%

// make a list of String including keyword
public List<String> search ( String keyword ) {
	
	if(str == null || str.equals("")) {
		return null;
	}
	
	List<String> lists = new ArrayList<String>();
	
	for(int i=0; i < ictList.length; i++){
		if(ictList[i].startsWith(str)){
			list.add(ictList[i]);
		}
	}

	return lists;
}
%>
<%
//  get parameter and search the categories
	String keyword = request.getParameter("keyword");
	List<String> keywordList = search(keyword);

//get number of categories and distinguish with '|'
out.print(keywordList.size());
out.print("|");

Iterator<String> it = keywordList.iterator();
while(it.hasNext()){
	String value = (String) it.next();
	out.print(value);
	if(keywordList.size()-1>0){
		out.print(","); //prevent last word does not containing ','
	}
}
%>