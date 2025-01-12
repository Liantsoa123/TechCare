<%@ page import="java.util.List" %>
<%@ page import="org.example.techcare.model.retour.Retour" %>
<%@ page import="org.example.techcare.model.repair.Repair" %>
<%
    List<Repair> repairs = (List<Repair>) request.getAttribute("repairs");
%>
<%--Page Header--%>
<div class="page-header">
    <h3 class="page-title"> Insert Retour </h3>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Retour</a></li>
            <li class="breadcrumb-item active" aria-current="page">Insertion Retour</li>
        </ol>
    </nav>
</div>
<%--Fin Page Header--%>
<%--Content--%>
<div class="row">
    <div class="col-md-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Insertion</h4>
                <form class="forms-sample" action="./retourServlet" method="post">

                    <div class="form-group">
                        <label for="retourDate">Retour date</label>
                        <input type="datetime-local" class="form-control" id="retourDate"
                               placeholder="Retour Date" name="retourDate">
                    </div>

                    <div class="form-group">
                        <label for="repair">Repair</label>
                        <select class="form-control" id="repair" name="repairId">
                            <%
                                for (Repair repair : repairs) { %>
                            <option value="<%=repair.getRepair_id()%>" ><%=repair.getLaptop().getCustomer().getName()%></option>
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