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
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg bg-body-tertiary">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="/recipe/list">모두의 레시피</a>
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

        <div class="row content">
            <div class="col">

                <div class="card">

                    <div class="card-body">
                        <h5 class="card-title">검색 및 필터 </h5>

                        <form action="/recipe/list" method="get">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                            <div class="mb-3">

                                <input type="checkbox" name="types" value="n"
                                       class="form-check-input"${pageRequestDTO.checkType("n")?"checked" : ""}>
                                <label class="form-check-label">요리명</label>
                                <input type="checkbox" name="types" value="w"
                                       class="form-check-input"${pageRequestDTO.checkType("w")?"checked" : ""}>
                                <label class="form-check-label">재료</label>

                                <input type="text" name="keyword" class="form-control"
                                       value="${pageRequestDTO.keyword}">
                            </div>
                            <div class="input-group mb-3 dueDateDiv">
                                <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}">
                                <input type="date" name="to" class="form-control" value="${pageRequestDTO.to}">
                            </div>
                            <div class="input-group mb-3">
                                <div class="float-end">
                                    <button class="btn btn-primary" type="submit">검색</button>
                                    <button class="btn btn-secondary clearBtn" type="reset">초기화</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>


            </div>

        </div>

        <div class="row content">

            <div class="col">

                <div class="card">

                    <div class="card-body">

                        <h5 class="card-title">레시피 목록</h5>
                        <button type="button" class="btn btn-primary insertRecipeBtn">레시피 작성</button>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">no</th>
                                <th scope="col">요리명</th>
                                <th scope="col">재료</th>
                                <th scope="col">최종 작성일</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pageResponseDTO.dtoList}" var="dto">
                                <tr>
                                    <th scope="row"><c:out value="${dto.recipe_id}"></c:out></th>
                                    <td><a href="/recipe/read?recipe_id=${dto.recipe_id}&${pageRequestDTO.link}"
                                           class="text-decoration-none">
                                        <c:out value="${dto.recipe_name}"></c:out>
                                    </a></td>
                                    <td><c:out value="${dto.ingredients}"></c:out></td>
                                    <td><c:out value="${dto.updateday}"></c:out></td>
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
                                    <li class="page-item ${pageResponseDTO.page == num ? "active" : ""}"
                                    ><a class="page-link" data-num="${num}" href="#">${num}</a></li>
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

        </div>
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
    document.querySelector(".insertRecipeBtn").addEventListener("click",
        function (e) {
            self.location = "/recipe/register"
                , false
        })

    document.querySelector(".pagination").addEventListener("click",
        function (e) {
            e.preventDefault()
            e.stopPropagation()

            const target = e.target

            if (target.tagName !== "A") {
                return
            }
            const num = target.getAttribute("data-num")

            const formObj = document.querySelector("form")
            formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`
            formObj.submit()


        }, false)


    document.querySelector(".clearBtn").addEventListener("click",
        function (e) {
            e.preventDefault();
            e.stopPropagation();

            self.location = "/recipe/list"
        }, false)


</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>