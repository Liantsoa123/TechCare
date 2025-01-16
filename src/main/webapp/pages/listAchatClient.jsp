<%@ page import="org.example.techcare.model.repair.Repair" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.techcare.model.retour.Retour" %>
<%@ page import="org.example.techcare.model.component.TypeComponent" %>
<%@ page import="org.example.techcare.model.laptotype.LaptopType" %><%
    List<Retour> retours = new ArrayList<>();
    if ((List<Retour>) request.getAttribute("retours") != null) {
        retours = (List<Retour>) request.getAttribute("retours");
    }
%>

<%--Page Header--%>
<div class="page-header">
    <h3 class="page-title"> Liste des Achats des clients aujourd'hui</h3>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">clients</a></li>
            <li class="breadcrumb-item active" aria-current="page">Listes clients</li>
        </ol>
    </nav>
</div>
<%--Fin Page Header--%>


<div class="row">
    <div class="col-md-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Search</h4>
                <form class="forms-sample" action="./retourServlet" method="post">

                    <input type="hidden" value="SC" name="mode" >
                    <div class="form-group">
                        <label for="retourDate">Retour date</label>
                        <input type="datetime-local" class="form-control" id="retourDate"
                               placeholder="Retour Date" name="retourDate">
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
            <h4 class="card-title">Listes Retour</h4>
            </p>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Nom Client</th>
                        <th>Email</th>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <%
                            int id = 1;
                            for ( Retour retour : retours ){
                        %>
                        <td><%=id%></td>

                                <%
                                id++;
                            }
                        %>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>