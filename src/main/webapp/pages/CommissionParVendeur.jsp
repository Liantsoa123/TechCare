<%@ page import="org.example.techcare.model.repair.Repair" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.techcare.model.retour.Retour" %>
<%@ page import="org.example.techcare.model.component.TypeComponent" %>
<%@ page import="org.example.techcare.dto.CommissionPeriod" %>
<%@ page import="java.util.HashMap" %>
<%
    List<CommissionPeriod> commissionPeriods = new ArrayList<>();
    if ((List<CommissionPeriod>) request.getAttribute("commissions") != null) {
        commissionPeriods = (List<CommissionPeriod>) request.getAttribute("commissions");
    }

    int id_sexe = -1;
    if (request.getParameter("id_sexe") != null) {
        id_sexe = Integer.parseInt(request.getParameter("id_sexe"));
    }
    HashMap<Integer, String> sexeMap = new HashMap<>();
    sexeMap.put(-1, "Tous");
    sexeMap.put(1, "Homme");
    sexeMap.put(2, "Femme");
%>

<%--Page Header--%>
<div class="page-header">
    <h3 class="page-title"> Liste des commissions</h3>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Commission</a></li>
            <li class="breadcrumb-item active" aria-current="page">Listes commission</li>
        </ol>
    </nav>
</div>
<%--Fin Page Header--%>


<div class="row">
    <div class="col-md-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Search</h4>
                <form class="forms-sample" action="./commissionServlet" method="post">
                    <p>
                        <input type="hidden" value="SC" name="mode">
                    <div class="form-group">
                        <label for="dateDebut">Date Debut</label>
                        <input type="date" class="form-control" id="dateDebut"
                               placeholder="Date Debut" name="dateDebut"
                               value="<%= request.getAttribute("dateDebut") != null ? request.getAttribute("dateDebut") : "" %>">
                    </div>
                    entre
                    <input type="hidden" value="SC" name="mode">
                    <div class="form-group">
                        <label for="dateFin">Date fin</label>
                        <input type="date" class="form-control" id="dateFin"
                               placeholder="Date fin" name="dateFin"
                               value="<%= request.getAttribute("dateFin") != null ? request.getAttribute("dateFin") : "" %>">
                    </div>
                    <div class="form-group">
                        <label for="customer">Sexe </label>
                        <select class="form-control" id="customer" name="id_sexe">
                            <% for (Integer key : sexeMap.keySet()) { %>
                            <option value="<%=key%>" <% if (key == id_sexe) { %> selected <% } %>><%=sexeMap.get(key)%>
                            </option>
                            <% } %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary mr-2">Search</button>
                    </p>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="col-lg-15 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Technician listes
            </h4>
            </p>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Nom</th>
                        <th>Email</th>
                        <th>Total vente</th>
                        <th>Total Commission</th>

                    </tr>
                    </thead>
                    <tbody>

                    <%
                        int id = 1;
                        for (CommissionPeriod commissionPeriod : commissionPeriods) {
                    %>
                    <tr>
                        <td><%=id%>
                        </td>
                        <td><%=commissionPeriod.getTechnician().getName()%>
                        </td>
                        <td><%=commissionPeriod.getTechnician().getEmail()%>
                        </td>
                        <td><%=commissionPeriod.getTotal()%>
                        </td>
                        <td><%=commissionPeriod.getTotalCommission()%>
                        </td>
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