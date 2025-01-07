<%@ page import="org.example.techcare.model.component.TypeComponent" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.techcare.model.repair.RepairStatus" %>
<%@ page import="org.example.techcare.model.repair.Repair" %>
<%@ page import="java.util.ArrayList" %>
<%
    List<TypeComponent> listTypeComponent = (List<TypeComponent>) request.getAttribute("TypeComponent");
    List<RepairStatus> repairStatuses =  new ArrayList<>();
    if ((List<RepairStatus>) request.getAttribute("repairstatus") != null){
        repairStatuses = (List<RepairStatus>) request.getAttribute("repairstatus");
    }

    List<Repair> repairs = new ArrayList<>();
    if ((List<Repair>) request.getAttribute("repairs") != null) {
        repairs = (List<Repair>) request.getAttribute("repairs");
    }
%>
<div class="page-header">
    <h3 class="page-title"> Liste Reparation </h3>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Reparation</a></li>
            <li class="breadcrumb-item active" aria-current="page"> Liste Reparation</li>
        </ol>
    </nav>
</div>
<%--Fin Page Header--%>
<%--Content--%>
<div class="row">
    <div class="col-md-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Recherche</h4>
                <form class="forms-sample" action="./repaireServlet"  method="post">
                    <div class="form-group">
                        <label for="typeComponent">Type Component</label>
                        <select class="form-control" id="typeComponent" name="typComponentId">
                            <%
                                for (TypeComponent typeComponent : listTypeComponent) { %>
                            <option value="<%=typeComponent.getType_component_id()%>"><%=typeComponent.getName()%></option>
                            <% }
                            %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary mr-2">Recherche</button>

                </form>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-6 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Listes Reparation</h4>
            </p>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nom Client</th>
                        <th>Numero de Serie</th>
                        <th>Marque</th>
                        <th>Model</th>
                        <th>Date de depot</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <% for(Repair repair : repairs){ %>
                        <td> <%=repair.getRepair_id()%></td>
                        <td> <%=repair.getLaptop().getCustomer().getName()%></td>
                        <td> <%=repair.getLaptop().getSerial_number()%></td>
                        <td> <%=repair.getLaptop().getBrand()%></td>
                        <td> <%=repair.getLaptop().getModel()%></td>
                        <td> <%=repair.getFiling_date()%></td>
                        <td> <%=repair.getRepairStatus().getName()%></td>

                        <%
                            }%>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%--Fin Content--%>