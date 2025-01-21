<%@ page import="java.util.List" %>
<%@ page import="org.example.techcare.model.repair.RepairType" %>
<%@ page import="org.example.techcare.model.technician.Technician" %>
<%@ page import="org.example.techcare.model.laptop.Laptop" %><%
    List<RepairType> repairTypeList = (List<RepairType>) request.getAttribute("repairTypeList");
    List<Technician> technicianList = (List<Technician>) request.getAttribute("technicianList");
    List<Laptop> laptopList = (List<Laptop>) request.getAttribute("laptopList");

%>

<%--Page Header--%>
<div class="page-header">
    <h3 class="page-title"> Insert Repair </h3>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Repair</a></li>
            <li class="breadcrumb-item active" aria-current="page">Insertion Repair</li>
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
                <form class="forms-sample" action="./repairServlet" method="post">

                    <div class="form-group">
                        <label for="customer">Customer </label>
                        <select class="form-control" id="customer" name="laptopId">
                            <%
                                for (Laptop laptop : laptopList) { %>
                            <option value="<%=laptop.getLaptop_id()%>" ><%=laptop.getCustomer().getName()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="repairType">Repair Type </label>
                        <select class="form-control" id="repairType" name="repairTypeId">
                            <%
                                for (RepairType repairType : repairTypeList) { %>
                            <option value="<%=repairType.getRepair_type_id()%>" ><%=repairType.getName()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="technician"> Technician </label>
                        <select class="form-control" id="technician" name="technicianId">
                            <%
                                for (Technician technician : technicianList) { %>
                            <option value="<%=technician.getTechnician_id()%>" ><%=technician.getName()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="repairDate">Repair date</label>
                        <input type="datetime-local" class="form-control" id="repairDate"
                               placeholder="Repair Date" name="repairDate">
                    </div>

                    <div class="form-group">
                        <label for="total">Total</label>
                        <input type="number" class="form-control" id="total"
                               placeholder="Total" name="total">
                    </div>

                    <button type="submit" class="btn btn-primary mr-2">Insert</button>

                </form>
            </div>
        </div>
    </div>
</div>
<%--Fin Content--%>