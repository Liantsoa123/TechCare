<%@ page import="java.util.List" %>
<%@ page import="org.example.techcare.model.ComponentRecommande" %>
<%
    List<ComponentRecommande> componentRecommandes = (List<ComponentRecommande>) request.getAttribute("componentRecommandes");
%>
<%--Page Header--%>
<div class="page-header">
    <h3 class="page-title"> Listes Components Recommandés </h3>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#">Composant</a></li>
            <li class="breadcrumb-item active" aria-current="page">Listes Composants</li>
        </ol>
    </nav>
</div>
<%--Fin Page Header--%>


<div class="row">
    <div class="col-md-6 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Search</h4>
                <form class="forms-sample" action="./componentRecommandeServlet" method="post">
                    <input type="hidden" name="mode" value="S" >
                    <div class="form-group">
                        <label for="mois"> Mois</label>
                        <input type="date" class="form-control" id="mois"
                               placeholder="Mois" name="dateRecommande">
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
            <h4 class="card-title">Listes Composants</h4>
            </p>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Composant</th>
                        <th>Type composant</th>
                        <th>Mois</th>

                    </thead>
                    <tbody>

                        <%
                            int id = 1;
                            for (ComponentRecommande componentRecommande1 : componentRecommandes) {
                        %>
                        <tr>
                        <td><%=id%></td>
                        <td><%=componentRecommande1.getComponent().getModel()%></td>
                        <td><%=componentRecommande1.getComponent().getTypeComponent().getName()%></td>
                        <td><%=componentRecommande1.getDate_recommande()%></td>
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