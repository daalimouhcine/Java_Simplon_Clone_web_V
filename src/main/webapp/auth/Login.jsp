<%--
  Created by IntelliJ IDEA.
  User: DarkFang
  Date: 11/7/2022
  Time: 7:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<%
    String error_login = (String) request.getAttribute("error_login");
    String email = "";
    String role = "";
    if(request.getAttribute("email") != null) {
        email = (String) request.getAttribute("email");
    }
    if(request.getAttribute("role") != null) {
        role = (String) request.getAttribute("role");
    }
%>
<!-- component -->
<div class="min-h-screen bg-gray-100 py-6 flex flex-col justify-center sm:py-12">
    <div class="relative py-3 sm:max-w-xl sm:mx-auto">
        <div
                class="absolute inset-0 bg-gradient-to-r from-blue-300 to-blue-600 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl">
        </div>
        <div class="relative px-4 py-10 bg-white shadow-lg sm:rounded-3xl sm:p-20">
            <% if (error_login != null) { %>
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 mb-5 rounded relative" role="alert">
                    <strong class="font-bold">Error!</strong>
                    <span class="block sm:inline"><%= error_login %></span>
                </div>
            <% } %>
            <form action="/AuthServlet" method="post" class="mx-auto">
                <div>
                    <h1 class="text-2xl text-center font-semibold">Login</h1>
                </div>
                <div class="mt-8">
                    <label class="text-base font-medium text-gray-900">Select your Role</label>
                    <p class="text-sm leading-5 text-gray-500">please select the role to login with</p>
                    <fieldset class="mt-4">
                        <legend class="sr-only">Roles</legend>
                        <div class="space-y-4 sm:flex sm:items-center sm:space-y-0 sm:space-x-10">
                            <div class="flex items-center">
                                <%
                                    if(role.equals("admin")) {
                                %>
                                    <input id="admin" value="admin" name="roleType" type="radio" checked class="focus:ring-indigo-500 h-4 w-4 text-indigo-600 border-gray-300">
                                <% } else { %>
                                    <input id="admin" value="admin" name="roleType" type="radio" class="focus:ring-indigo-500 h-4 w-4 text-indigo-600 border-gray-300">
                                <% } %>
                                <label for="admin" class="ml-3 block text-sm font-medium text-gray-700"> Admin </label>
                            </div>

                            <div class="flex items-center">
                                <%
                                    if(role.equals("teacher")) {
                                %>
                                <input id="teacher" value="teacher" name="roleType" type="radio" checked class="focus:ring-indigo-500 h-4 w-4 text-indigo-600 border-gray-300">
                                <% } else { %>
                                <input id="teacher" value="teacher" name="roleType" type="radio" class="focus:ring-indigo-500 h-4 w-4 text-indigo-600 border-gray-300">
                                <% } %>
                                <label for="teacher" class="ml-3 block text-sm font-medium text-gray-700"> Teacher </label>
                            </div>

                            <div class="flex items-center">
                                <%
                                    if(role.equals("student")) {
                                %>
                                    <input id="student" value="student" name="roleType" type="radio" checked class="focus:ring-indigo-500 h-4 w-4 text-indigo-600 border-gray-300">
                                <% } else { %>
                                    <input id="student" value="student" name="roleType" type="radio" class="focus:ring-indigo-500 h-4 w-4 text-indigo-600 border-gray-300">
                                <% } %>
                                <label for="student" class="ml-3 block text-sm font-medium text-gray-700"> Student </label>
                            </div>
                        </div>
                    </fieldset>
                </div>

                <div class="divide-y divide-gray-200">
                    <div class="py-8 text-base leading-6 space-y-4 text-gray-700 sm:text-lg sm:leading-7">
                        <div class="relative">
                            <input autocomplete="off" required id="email" name="email" type="text" value="<%=email%>" class="peer placeholder-transparent h-10 w-full border-b-2 border-gray-300 text-gray-900 focus:outline-none focus:borer-rose-600" placeholder="Email address" />
                            <label for="email" class="absolute left-0 -top-3.5 text-gray-600 text-sm peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-440 peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm">Email Address</label>
                        </div>
                        <div class="relative">
                            <input autocomplete="off" required id="password" name="password" type="password" class="peer placeholder-transparent h-10 w-full border-b-2 border-gray-300 text-gray-900 focus:outline-none focus:borer-rose-600" placeholder="Password" />
                            <label for="password" class="absolute left-0 -top-3.5 text-gray-600 text-sm peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-440 peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm">Password</label>
                        </div>

                    </div>
                        <div class="w-full flex justify-center border-none">
                            <button type="submit" class="relative mx-auto px-5 py-3 overflow-hidden font-medium text-blue-600 bg-blue-100 border border-blue-100 rounded-lg shadow-inner group">
                                <span class="absolute top-0 left-0 w-0 h-0 transition-all duration-200 border-t-2 border-blue-600 group-hover:w-full ease"></span>
                                <span class="absolute bottom-0 right-0 w-0 h-0 transition-all duration-200 border-b-2 border-blue-600 group-hover:w-full ease"></span>
                                <span class="absolute top-0 left-0 w-full h-0 transition-all duration-300 delay-200 bg-blue-600 group-hover:h-full ease"></span>
                                <span class="absolute bottom-0 left-0 w-full h-0 transition-all duration-300 delay-200 bg-blue-600 group-hover:h-full ease"></span>
                                <span class="absolute inset-0 w-full h-full duration-300 delay-300 bg-blue-900 opacity-0 group-hover:opacity-100"></span>
                                <span class="relative transition-colors duration-300 delay-200 group-hover:text-white ease">Login</span>
                            </button>
                        </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
