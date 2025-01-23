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
%>

<%--Page Header--%>
<div class="page-header">
    <h3 class="page-title"> Liste des commissions hommes et femmes</h3>
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
            <h4 class="card-title">Liste des Vendeurs</h4>
            <p>Commisssion Homme = </p>
            <p>Commission Femme = </p>

        </div>
    </div>
</div>