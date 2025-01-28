<%@ page import="java.util.List" %>
<%@ page import="org.example.techcare.model.component.Component" %>
<%
    List<Component> components = (List<Component>) request.getAttribute("components");
%>
<%--Page Header--%>
<div class="page-header">
    <h3 class="page-title"> Insert Prix Component </h3>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Component</a></li>
            <li class="breadcrumb-item active" aria-current="page">Insertion Prix Component</li>
        </ol>
    </nav>
</div>
<%--Fin Page Header--%>
<%--Content--%>
<div class="row">
    <div class="col-md-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Insertion Prix </h4>
                <form class="forms-sample" action="./HistoPrixServlet" method="post">
                    <div class="form-group">
                        <label for="daty">Date</label>
                        <input type="date" class="form-control" id="daty"
                               placeholder="date" name="date">
                    </div>

                    <div class="form-group">
                        <label for="prix"> Prix</label>
                        <input type="number" class="form-control" id="prix"
                               placeholder="Prix" name="prix">
                    </div>

                    <div class="form-group">
                        <label for="composant">Composant</label>
                        <select class="form-control" id="composant" name="componentId">
                            <%
                                for (Component component : components) { %>
                            <option value="<%=component.getComponent_id()%>" ><%=component.getModel()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary mr-2">Insert</button>
                </form>
            </div>
        </div>
    </div>
</div>
<%--Fin Content--%>