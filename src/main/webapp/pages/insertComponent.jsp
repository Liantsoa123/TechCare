<%@ page import="java.util.List" %>
<%@ page import="org.example.techcare.model.component.TypeComponent" %>
<%@ page import="org.example.techcare.model.brandcomponent.BrandComponent" %>
<%
    List<TypeComponent> typeComponents = (List<TypeComponent>) request.getAttribute("typeComponents");
    List<BrandComponent> brandComponents = (List<BrandComponent>) request.getAttribute("brandComponents");
%>

<%--Page Header--%>
<div class="page-header">
    <h3 class="page-title"> Insert Component </h3>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Component</a></li>
            <li class="breadcrumb-item active" aria-current="page">Insertion Component</li>
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
                <form class="forms-sample">
                    <input type="hidden" name="model" value="insert">

                    <div class="form-group">
                        <label for="model">Capacity</label>
                        <input type="number" class="form-control" id="model"
                               placeholder="Model" name="model">
                    </div>


                    <div class="form-group">
                        <label for="typeComponent">Type Component</label>
                        <select class="form-control" id="typeComponent" name="typeComponentId">
                            <%
                                for (TypeComponent typeComponent : typeComponents) { %>
                            <option value="<%=typeComponent.getType_component_id()%>"><%=typeComponent.getName()%></option>
                            <% }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="brand">Brand</label>
                        <select class="form-control" id="brand" name="brandComponentId">
                            <%
                                for (BrandComponent brandComponent : brandComponents) { %>
                            <option value="<%=brandComponent.getBrandComponentId()%>"><%=brandComponent.getName()%></option>
                            <% }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="Capacity">Capacity</label>
                        <input type="number" class="form-control" id="Capacity"
                               placeholder="Capacity" name="capacity">
                    </div>

                    <button type="submit" class="btn btn-primary mr-2">Insert</button>
                    <button class="btn btn-dark">Cancel</button>
                </form>
            </div>
        </div>
    </div>
</div>
<%--Fin Content--%>