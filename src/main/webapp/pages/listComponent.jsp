<%@ page import="org.example.techcare.model.brandcomponent.BrandComponent" %>
<%@ page import="org.example.techcare.model.component.TypeComponent" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.techcare.model.component.Component" %>
<%@ page import="java.util.ArrayList" %>
<%
    List<TypeComponent> typeComponents = (List<TypeComponent>) request.getAttribute("typeComponents");
    List<BrandComponent> brandComponents = (List<BrandComponent>) request.getAttribute("brandComponents");
    List<Component> components = request.getAttribute("components") != null ? (List<Component>) request.getAttribute("components") : new ArrayList<>();
    int typeComponentId = request.getAttribute("typeComponentId")!= null?(int) request.getAttribute("typeComponentId"):0;
    int brandComponentId = request.getAttribute("brandComponentId")!= null?(int) request.getAttribute("brandComponentId"):0;
%>
<%--Page Header--%>
<div class="page-header">
    <h3 class="page-title"> Listes Components </h3>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Component</a></li>
            <li class="breadcrumb-item active" aria-current="page">Listes Components</li>
        </ol>
    </nav>
</div>
<%--Fin Page Header--%>


<div class="row">
    <div class="col-md-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Search</h4>
                <form class="forms-sample" action="./componentServlet" method="post">
                    <input type="hidden" name="mode" value="search">
                    <div class="form-group">
                        <label for="typeComponentId">Type Component</label>
                        <select class="form-control" id="typeComponentId" name="typeComponentId">
                            <option value="0">All</option>
                            <%
                                for (TypeComponent typeComponent : typeComponents) { %>
                            <option value="<%=typeComponent.getType_component_id()%>"  <% if (typeComponent.getType_component_id() == typeComponentId) { %>
                                    selected <% } %> ><%=typeComponent.getName()%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="brandComponentId">Brand</label>
                        <select class="form-control" id="brandComponentId" name="brandComponentId">
                            <option value="0">All</option>
                            <%
                                for (BrandComponent brandComponent : brandComponents) { %>
                            <option value="<%=brandComponent.getBrandComponentId()%>" <% if ( brandComponent.getBrandComponentId() == brandComponentId ){%> selected <%}%> ><%=brandComponent.getName()%>
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


<div class="col-lg-6 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Listes Component</h4>
            </p>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Model</th>
                        <th>Type</th>
                        <th>Brand</th>
                        <th>Unite price</th>
                        <th>Capacity</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        int id = 1;
                        for (Component component : components) { %>
                    <tr>
                        <td><%=id%>
                        </td>
                        <td><%=component.getModel()%>
                        </td>
                        <td><%=component.getTypeComponent().getName()%>
                        </td>
                        <td><%=component.getBrandComponent().getName()%>
                        </td>
                        <td><%=component.getUnite_price()%>
                        </td>
                        <td><%=component.getCapacity()%>
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

