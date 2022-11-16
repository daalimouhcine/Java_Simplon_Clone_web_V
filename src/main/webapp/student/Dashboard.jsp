<%@ page import="com.brief.java_simplon_clone_web_v.entities.StudentsEntity" %>
<%@ page import="com.brief.java_simplon_clone_web_v.entities.PromosEntity" %><%--
  Created by IntelliJ IDEA.
  User: DarkFang
  Date: 11/16/2022
  Time: 1:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    StudentsEntity student = (StudentsEntity) request.getSession().getAttribute("student");
    PromosEntity promo = (PromosEntity) request.getSession().getAttribute("promo");
%>
<html>
<head>
    <title>Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<div class="min-h-full flex-col w-full justify-center">
    <header class="pb-24 bg-gradient-to-r from-sky-800 to-cyan-600">
        <div class="max-w-3xl mx-auto px-4 sm:px-6 lg:max-w-7xl lg:px-8">
            <div class="relative flex flex-wrap items-center justify-center lg:justify-between">

                <!-- Right section on desktop -->
                <div class="hidden lg:ml-4 lg:flex lg:items-center lg:py-5 lg:pr-0.5">

                    <!-- Profile dropdown -->

                <div class="w-full py-5 lg:border-t lg:border-white lg:border-opacity-20">
                    <div class="lg:grid lg:grid-cols-3 lg:gap-8 lg:items-center">
                        <!-- Left nav -->
                        <div class="hidden lg:block lg:col-span-2">
                            <nav class="flex space-x-4">
                                <a href="#" class="text-white text-sm font-medium rounded-md bg-white bg-opacity-0 px-3 py-2 hover:bg-opacity-10" aria-current="page"> Dashboard </a>

                                <a href="#" class="text-cyan-100 text-sm font-medium rounded-md bg-white bg-opacity-0 px-3 py-2 hover:bg-opacity-10"> Promo </a>

                                <a href="#" class="text-cyan-100 text-sm font-medium rounded-md bg-white bg-opacity-0 px-3 py-2 hover:bg-opacity-10"> Briefs </a>

                                <a href="#" class="text-cyan-100 text-sm font-medium rounded-md bg-white bg-opacity-0 px-3 py-2 hover:bg-opacity-10"> Profile </a>

                                <form action="/Logout" method="post">
                                    <input type="hidden" name="action" value="logout"/>
                                    <button type="submit" class="group flex items-center px-2 py-2 text-sm leading-6 font-medium rounded-md text-cyan-100 hover:text-white hover:bg-cyan-600">
                                        <svg class="mr-4 flex-shrink-0 h-6 w-6 text-cyan-200" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                                            <path fill-rule="evenodd" d="M3 3a1 1 0 0 0-1 1v12a1 1 0 1 0 2 0V4a1 1 0 0 0-1-1Zm10.293 9.293a1 1 0 0 0 1.414 1.414l3-3a1 1 0 0 0 0-1.414l-3-3a1 1 0 1 0-1.414 1.414L14.586 9H7a1 1 0 1 0 0 2h7.586l-1.293 1.293Z" clip-rule="evenodd"/>
                                        </svg>
                                        Logout
                                    </button>
                                </form>

                            </nav>
                        </div>
                    </div>
                </div>

                <!-- Menu button -->
                <div class="absolute right-0 flex-shrink-0 lg:hidden">
                    <!-- Mobile menu button -->
                    <button type="button" class="bg-transparent p-2 rounded-md inline-flex items-center justify-center text-cyan-200 hover:text-white hover:bg-white hover:bg-opacity-10 focus:outline-none focus:ring-2 focus:ring-white" aria-expanded="false">
                        <span class="sr-only">Open main menu</span>
                        <svg class="block h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" aria-hidden="true">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
                        </svg>
                        <svg class="hidden h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" aria-hidden="true">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                        </svg>
                    </button>
                </div>
            </div>
        </div>

        <!-- Mobile menu, show/hide based on mobile menu state. -->

        </div>
    </header>
    <main class="-mt-24 mx-auto pb-8">
        <div class="max-w-3xl mx-auto px-4 sm:px-6 lg:max-w-7xl lg:px-8">
            <h1 class="sr-only">Profile</h1>
            <!-- Main 3 column grid -->
            <div class="">
                <!-- Left column -->
                <div class="grid grid-cols-1 gap-4 lg:col-span-2">
                    <!-- Welcome panel -->
                    <section aria-labelledby="profile-overview-title">
                        <div class="rounded-lg bg-white overflow-hidden shadow">
                            <h2 class="sr-only" id="profile-overview-title">Profile Overview</h2>
                            <div class="bg-white p-6">
                                <div class="sm:flex sm:items-center sm:justify-between">
                                    <div class="sm:flex sm:space-x-5">
                                        <div class="flex-shrink-0">
                                            <span class="inline-flex items-center justify-center h-12 w-12 rounded-full bg-cyan-700">
                                              <span class="text-lg font-medium leading-none text-cyan-200"><%=student.getFullname().toUpperCase().charAt(0)%></span>
                                            </span>
                                        </div>
                                        <div class="mt-4 text-center sm:mt-0 sm:pt-1 sm:text-left">
                                            <p class="text-sm font-medium text-gray-600">Welcome back,</p>
                                            <p class="text-xl font-bold text-gray-900 sm:text-2xl"><%=student.getFullname()%></p>
                                            <p class="text-sm font-medium text-gray-600">
                                                <% if(promo != null) { %>
                                                    <%=promo.getName()%>
                                                <% } else { %>
                                                    No promo
                                                <% } %>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="mt-5 flex justify-center sm:mt-0">
                                        <a href="#" class="flex justify-center items-center px-4 py-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50"> View profile </a>
                                    </div>
                                </div>
                            </div>
                            <div class="border-t border-gray-200 bg-gray-50 grid grid-cols-1 divide-y divide-gray-200 sm:grid-cols-3 sm:divide-y-0 sm:divide-x">
                                <div class="px-6 py-5 text-sm font-medium text-center">
                                    <span class="text-gray-900">0</span>
                                    <span class="text-gray-600">Brief Assigned</span>
                                </div>

                                <div class="px-6 py-5 text-sm font-medium text-center">
                                    <span class="text-gray-900">0</span>
                                    <span class="text-gray-600">Brief appended</span>
                                </div>

                                <div class="px-6 py-5 text-sm font-medium text-center">
                                    <span class="text-gray-900">0</span>
                                    <span class="text-gray-600">Brief validated</span>
                                </div>
                            </div>
                        </div>
                    </section>

                    <!-- Actions panel -->
                </div>

                <!-- Right column -->
            </div>
        </div>
    </main>
</div>
</body>
</html>
