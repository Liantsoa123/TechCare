<%@ page import="org.example.techcare.model.repair.Repair" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.techcare.model.retour.Retour" %>
<%@ page import="org.example.techcare.model.component.TypeComponent" %>
<%@ page import="org.example.techcare.dto.CommissionPeriod" %><%
    List<CommissionPeriod> commissionPeriods = new ArrayList<>();
    if ((List<CommissionPeriod>) request.getAttribute("commissions") != null) {
        commissionPeriods = (List<CommissionPeriod>) request.getAttribute("commissions");
    }

    String sexe = "";
    if ( request.getAttribute("sexe") !=null ){
        sexe = request.getAttribute("sexe").toString();
    }

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
                        <input type="hidden" value="SC" name="mode" >
                        <div class="form-group">
                            <label for="dateDebut">Date Debut</label>
                            <input type="date" class="form-control" id="dateDebut"
                               placeholder="Date Debut" name="dateDebut">
                        </div>
                      entre
                        <input type="hidden" value="SC" name="mode" >
                        <div class="form-group">
                            <label for="dateFin">Date fin</label>
                            <input type="date" class="form-control" id="dateFin"
                                placeholder="Date fin" name="dateFin">
                        </div>
                        <div class="form-group">
                            <label for="customer">Sexe </label>
                            <select class="form-control" id="customer" name="id_sexe">
                                <option value="1">Homme</option>
                                 <option value="2">Femme</option>
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
            <h4 class="card-title">Liste des Vendeurs <%=sexe%> </h4>
            </p>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Nom </th>
                        <th>Email</th>
                        <th>Commission </th>

                    </tr>
                    </thead>
                    <tbody>

                        <%
                            int id = 1;
                            for ( CommissionPeriod commissionPeriod : commissionPeriods ){
                        %>
                        <tr>
                        <td><%=id%></td>
                        <td><%=commissionPeriod.getTechnician().getName()%></td>
                        <td><%=commissionPeriod.getTechnician().getEmail()%></td>
                        <td><%=commissionPeriod.getTotal()%></td>
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