<%@ page import="org.example.techcare.model.repair.Repair" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.techcare.model.retour.Retour" %>
<%@ page import="org.example.techcare.model.component.TypeComponent" %>
<%@ page import="org.example.techcare.model.laptotype.LaptopType" %><%
    List<TypeComponent> typeComponents = (List<TypeComponent>) request.getAttribute("typecomponents");
    List<LaptopType> laptopTypes = (List<LaptopType>) request.getAttribute("laptoptypes");
    List<Retour> retours = new ArrayList<>();
    if ((List<Retour>) request.getAttribute("retours") != null) {
        retours = (List<Retour>) request.getAttribute("retours");
    }
%>

<%--Page Header--%>
<div class="page-header">
    <h3 class="page-title"> Listes Retours </h3>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Retour</a></li>
            <li class="breadcrumb-item active" aria-current="page">Listes Retours</li>
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
                    <div class="form-group">
                        <input type="hidden" name="mode" value="S" >
                        <label for="typeComponent">Type Component</label>
                        <select class="form-control" id="typecomponent" name="typecomponentId">
                            <%
                                for (TypeComponent typeComponent : typeComponents) { %>
                            <option value="<%=typeComponent.getType_component_id()%>" ><%=typeComponent.getName()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <input type="hidden" name="mode" value="S" >
                        <label for="laptopType">Laptop Type</label>
                        <select class="form-control" id="laptopType" name="laptopTypeId">
                            <%
                                for (LaptopType laptopType : laptopTypes) { %>
                            <option value="<%=laptopType.getLaptopTypeId()%>" ><%=laptopType.getName()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <input type="hidden" name="mode" value="S" >
                        <label for="repairType">Repair Type</label>
                        <select class="form-control" id="repairType" name="repairType">
                           <option value="true" >Upgrade</option>
                           <option value="false" >Normal</option>
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
            <h4 class="card-title">Listes Retour</h4>
            </p>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Customer</th>
                        <th>Laptop serial number</th>
                        <th>Type Laptop</th>
                        <th>Type Component</th>
                        <th>Old Component</th>
                        <th>New Component</th>
                        <th>Date Retour</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <%
                            int id = 1;
                            for ( Retour retour : retours ){
                        %>
                         <td><%=id%></td>
                        <td><%=retour.getRepair().getLaptop().getCustomer().getName()%></td>
                        <td><%=retour.getRepair().getLaptop().getSerial_number()%></td>
                        <td><%=retour.getRepair().getLaptop().getLaptopType().getName()%></td>
                        <td><%=retour.getNewComponent().getTypeComponent().getName()%> </td>
                        <td><%=retour.getOldComponent().getModel()%></td>
                        <td><%=retour.getNewComponent().getModel()%></td>
                        <td><%=retour.getRetour_date()%>/td>
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