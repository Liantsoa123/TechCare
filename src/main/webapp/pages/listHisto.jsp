<%@ page import="java.util.List" %>
<%@ page import="org.example.techcare.model.histo.HistoriquePrix" %>
<%@ page import="org.example.techcare.model.component.Component" %>
<%
    List<HistoriquePrix> historiquePrixList = (List<HistoriquePrix>) request.getAttribute("historiques");
    List<Component> components = (List<Component>) request.getAttribute("components");
%>

<%--Page Header--%>
<div class="page-header">
    <h3 class="page-title"> Historiques Prix</h3>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Component</a></li>
            <li class="breadcrumb-item active" aria-current="page">Historique</li>
        </ol>
    </nav>
</div>
<%--Fin Page Header--%>


<div class="row">
    <div class="col-md-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Search</h4>
                <form class="forms-sample" action="./HistoPrixServlet" method="post">
                    <input type="hidden" name="mode" value="search">
                    <div class="form-group">
                        <label for="composant">Composant</label>
                        <select class="form-control" id="composant" name="componentId">
                            <%
                                for (Component component : components) { %>
                            <option value="<%=component.getComponent_id()%>"><%=component.getModel()%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary mr-2">Search</button>

                </form>
            </div>
        </div>
    </div>
</div>


<div class="col-lg-15 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Listes Historique Prix</h4>
            </p>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Component</th>
                        <th>Type Component</th>
                        <th>Prix</th>
                        <th>Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        int id = 1;
                        for (HistoriquePrix historiquePrix : historiquePrixList) {
                    %>
                    <tr>
                        <td><%=id%></td>
                        <td><%=historiquePrix.getComponent().getModel()%></td>
                        <td><%=historiquePrix.getComponent().getTypeComponent().getName()%></td>
                        <td><%=historiquePrix.getPrix()%></td>
                        <td><%=historiquePrix.getDateHisto()%></td>
                    </tr>
                    <%
                            id++;
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>