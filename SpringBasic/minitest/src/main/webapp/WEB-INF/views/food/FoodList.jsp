<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!--        <h1>Header</h1>-->
        <!--        네비게이션바 추가 시작-->
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg bg-body-tertiary">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">Navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                                aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Features</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Pricing</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>

            </div>
        </div>
        <!--        네비게이션바 추가 끝-->

        <!--        class="row content"-->
        <div class="row content">
            <!--        col-->
            <div class="col">
                <!--        카드 시작 부분-->
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <%--                        food List 부분 작성--%>
                        <h5 class="card-title">리스트 목록</h5>
                        <button type="button" class="btn btn-primary insertfoodBtn">메뉴 작성</button>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Tno</th>
                                <th scope="col">Menu</th>
                                <th scope="col">Price</th>
                                <th scope="col">DueDate</th>
                                <th scope="col">Finished</th>
                            </tr>
                            </thead>

                            <tbody>

                                <c:forEach items="${pageResponseDTO.dtoList}" var="dto">
                                    <tr>
                                        <th scope="row"><c:out value="${dto.tno}"></c:out></th>
                                        <td><a href="/food/foodread?tno=${dto.tno}&${pageRequestDTO.link}" class="text-decoration-none">
                                        <c:out value="${dto.menu}"></c:out>
                                        </a></td>
                                        <td><c:out value="${dto.price}"></c:out></td>
                                        <td><c:out value="${dto.dueDate}"></c:out></td>
                                        <td><c:out value="${dto.finished}"></c:out></td>
                                    </tr>
                                </c:forEach>
                            </tbody>

                        </table>

                        <div class="float-end">
                            <ul class="pagination">

                                <c:if test="${pageResponseDTO.prev}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${pageResponseDTO.page - 1}">Previous</a>
                                    </li>
                                </c:if>

                                <c:forEach begin="${pageResponseDTO.start}"
                                           end="${pageResponseDTO.end}" var="num">
                                    <li class="page-item ${pageResponseDTO.page == num ? "active" : ""}">
                                        <a class="page-link" data-num="${num}" href="#">${num}</a></li>
                                </c:forEach>


                                <c:if test="${pageResponseDTO.next}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${pageResponseDTO.end + 1}">Next</a>
                                    </li>
                                </c:if>

                            </ul>
                        </div>

                    </div>
                </div>

            </div>
            <!--        col-->
        </div>
        <!--        class="row content"-->
    </div>

    <div class="row footer">
        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>

<script>
    const serverValidResult = {};

    <c:forEach items="${errors}" var="error">
    serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>
    console.log(serverValidResult)
</script>

<script>
    document.querySelector(".insertfoodBtn").addEventListener("click",
        function (e) {
            self.location = "/food/foodRegister"
                , false
        })


    //페이지
    document.querySelector(".pagination").addEventListener("click",
        function (e) {
            e.preventDefault()
            e.stopPropagation()

            const target = e.target
            if (target.tagName !== "A") {
                return
            }
            const num = target.getAttribute("data-num")
            self.location = `/food/FoodList?page=\${num}`
        }, false)
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>