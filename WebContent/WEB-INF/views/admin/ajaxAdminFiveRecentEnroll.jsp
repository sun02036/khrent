<%@page import="com.kh.semi.member.model.service.MemberService"%>
<%@page import="com.kh.semi.member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Member> list = (List<Member>) request.getAttribute("list");
%>

<%
for (Member member : list) {
%>
					<tr role="row" class="odd">
					<th scope="row" tabindex="0" class="sorting_1">
         
									<div class="patient_thumb d-flex align-items-center" id="odd">
                                                <p><%= member.getMemberId() %></p>
                                            </div>
                                        </th>
                                        <td><%= member.getMemberName() %></td>
                                        <td><%= MemberService.ADMIN_ROLE.equals(member.getMemberRole()) ? "관리자" : "일반" %></td>
                                        <td><%= member.getRegDate() %></td>
                    </tr>
<%
}
%>
                                       
