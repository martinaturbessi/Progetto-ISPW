<%@ page import="entity.Filter" %>
<%@ page import="entity.SortingCriterium" %>
<%@ page import="entity.ValutazioniUtente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="thread.ImportEvaluationTimer" %>

<%--
  Created by IntelliJ IDEA.
  User: martinaturbessi
  Date: 2019-01-09
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!-- Si dichiara la variabile Bean e istanzia un oggetto Bean -->
<jsp:useBean id="valutazioniUtenteBean" scope="request" class="bean.ValutazioniUtenteBean"/>
<jsp:useBean id="ordinamentoBean" scope="request" class="bean.OrdinamentoBean"/>
<jsp:useBean id="filtraggioBean" scope="request" class="bean.FiltraggioBean"/>

<!-- Mappa automaticamente tutti gli attributi dell'oggetto Bean e le proprietà JSP -->
<jsp:setProperty name="valutazioniUtenteBean" property="*"/>
<jsp:setProperty name="ordinamentoBean" property="*"/>
<jsp:setProperty name="filtraggioBean" property="*"/>


<%
    ArrayList<ValutazioniUtente> uEs = new ArrayList<ValutazioniUtente>();
    ImportEvaluationTimer importEvaluationTimer = new ImportEvaluationTimer();
    uEs = valutazioniUtenteBean.getEvaluation();
    importEvaluationTimer.start();
%>

<%
    String order = "";
    if (request.getParameter("ordinaCb") != null) {
        if (request.getParameter("ordinaCb").equalsIgnoreCase("Valutazioni recenti")) {
            order = "Valutazioni recenti";
            ordinamentoBean.setOrder(order);
        } else if (request.getParameter("ordinaCb").equalsIgnoreCase("Valutazioni meno recenti")) {
            order = "Valutazioni meno recenti";
            ordinamentoBean.setOrder(order);
        } else if (request.getParameter("ordinaCb").equalsIgnoreCase("Valutazioni migliori")) {
            order = "Valutazioni migliori";
            ordinamentoBean.setOrder(order);
        } else if (request.getParameter("ordinaCb").equalsIgnoreCase("Valutazioni peggiori")) {
            order = "Valutazioni peggiori";
            ordinamentoBean.setOrder(order);
        }
    }
%>


<%
    String categoria = "";
    if (request.getParameter("cbCategoria") != null) {
        if (request.getParameter("cbCategoria").equalsIgnoreCase("Tutte le categorie")) {
            categoria = "Tutte le categorie";
            filtraggioBean.setCategoria(categoria);
        } else if (request.getParameter("cbCategoria").equalsIgnoreCase("Generica")) {
            categoria = "Generica";
            filtraggioBean.setCategoria(categoria);
        } else if (request.getParameter("cbCategoria").equalsIgnoreCase("Pulizia")) {
            categoria = "Pulizia";
            filtraggioBean.setCategoria(categoria);
        } else if (request.getParameter("cbCategoria").equalsIgnoreCase("Educazione")) {
            categoria = "Educazione";
            filtraggioBean.setCategoria(categoria);
        } else if (request.getParameter("cbCategoria").equalsIgnoreCase("Disponibilità")) {
            categoria = "Disponibilità";
            filtraggioBean.setCategoria(categoria);
        }
    } else {
        categoria = "Tutte";
        filtraggioBean.setCategoria(categoria);
    }
%>

<%
    String stelle = "";
    if (request.getParameter("cbStelle") != null) {
        if (request.getParameter("cbStelle").equalsIgnoreCase("Tutte")) {
            stelle = "Tutte";
            filtraggioBean.setStelle(stelle);
        } else if (request.getParameter("cbStelle").equalsIgnoreCase("5 stelle")) {
            stelle = "5 stelle";
            filtraggioBean.setStelle(stelle);
        } else if (request.getParameter("cbStelle").equalsIgnoreCase("4 stelle")) {
            stelle = "4 stelle";
            filtraggioBean.setStelle(stelle);
        } else if (request.getParameter("cbStelle").equalsIgnoreCase("3 stelle")) {
            stelle = "3 stelle";
            filtraggioBean.setStelle(stelle);
        } else if (request.getParameter("cbStelle").equalsIgnoreCase("2 stelle")) {
            stelle = "2 stelle";
            filtraggioBean.setStelle(stelle);
        } else if (request.getParameter("cbStelle").equalsIgnoreCase("1 stella")) {
            stelle = "1 stella";
            filtraggioBean.setStelle(stelle);
        }
    }
%>

<%
    String lingua = "";
    if (request.getParameter("cbLingua") != null) {
        if (request.getParameter("cbLingua").equalsIgnoreCase("Tutte")) {
            lingua = "Tutte";
            filtraggioBean.setLingua(lingua);
        } else if (request.getParameter("cbLingua").equalsIgnoreCase("ita")) {
            lingua = "ita";
            filtraggioBean.setLingua(lingua);
        } else if (request.getParameter("cbLingua").equalsIgnoreCase("en")) {
            lingua = "en";
            filtraggioBean.setLingua(lingua);
        } else if (request.getParameter("cbLingua").equalsIgnoreCase("es")) {
            lingua = "es";
            filtraggioBean.setLingua(lingua);
        } else if (request.getParameter("cbLingua").equalsIgnoreCase("fr")) {
            lingua = "fr";
            filtraggioBean.setLingua(lingua);
        } else if (request.getParameter("cbLingua").equalsIgnoreCase("de")) {
            lingua = "de";
            filtraggioBean.setLingua(lingua);
        }
    }
%>

<%
    String range = "";
    if (request.getParameter("cbRange") != null) {
        if (request.getParameter("cbRange").equalsIgnoreCase("Tutte")) {
            range = "Tutte";
            filtraggioBean.setRange(range);
        } else if (request.getParameter("cbRange").equalsIgnoreCase("Mar-Mag")) {
            range = "Mar-Mag";
            filtraggioBean.setRange(range);
        } else if (request.getParameter("cbRange").equalsIgnoreCase("Giu-Ago")) {
            range = "Giu-Ago";
            filtraggioBean.setRange(range);
        } else if (request.getParameter("cbRange").equalsIgnoreCase("Sett-Nov")) {
            range = "Sett-Nov";
            filtraggioBean.setRange(range);
        } else if (request.getParameter("cbRange").equalsIgnoreCase("Dic-Feb")) {
            range = "Dic-Feb";
            filtraggioBean.setRange(range);
        }
    }
%>


<%
    if (request.getParameter("filtra") != null) {
        try {
            Filter filter = filtraggioBean.setFilter();
            uEs = filtraggioBean.filtraValutazioni(filter);
            if (uEs.isEmpty()) {
%>
<script>
    alert("Nessuna valutazione trovata con i metodi di filtraggio inseriti!");
</script>
<%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>

<%
    if (request.getParameter("ordina") != null) {
        try {
            SortingCriterium sortingCriterium = ordinamentoBean.setSortingCriterium();
            uEs = ordinamentoBean.ordinaValutazioni(uEs, sortingCriterium);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>ValutazioniUtente</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="libs/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<header>
    <h1><b>Valutazioni utente</b></h1>
    <img src="Img/man.png" width="128" height="128">
    <h2>
        <%
            double media_generica = valutazioniUtenteBean.setMedia("Generica");
        %>
        <font color="#277c73"><%=media_generica%>
        </font>
        <small>/ 5</small>
        <br>
        <%
            double media_pul = valutazioniUtenteBean.setMedia("Pulizia");
        %>
        Pulizia: <font color="#277c73"><%=media_pul%>
    </font>
        <small>/ 5</small>
        <br>
        <%
            double media_edu = valutazioniUtenteBean.setMedia("Educazione");
        %>
        Educazione: <font color="#277c73"><%=media_edu%>
    </font>
        <small>/ 5</small>
        <br>
        <%
            double media_disp = valutazioniUtenteBean.setMedia("Disponibilità");
        %>
        Disponibilità: <font color="#277c73"><%=media_disp%>
    </font>
        <small>/ 5</small>
    </h2>
    <br>
</header>

<section>
    <nav>
        <div class="row">
            <div class="dropdown">
                <button onclick="myFunctionOrdinamento()" class="dropbtn" style="margin-right: 10px;"><b>Ordina per:</b>
                </button>
                <div id="myDropdownOrdina" class="dropdown-content">
                    <form action="ValutazioniUtente.jsp" method="post">
                        <select name="ordinaCb" id="cbOrdinazione" required
                                style="width:200px; font-size: 12px; border-color: #277c73;">
                            <option value="" disabled>Ordina per:</option>
                            <option value="Valutazioni recenti">Valutazioni recenti</option>
                            <option value="Valutazioni meno recenti">Valutazioni meno recenti</option>
                            <option value="Valutazioni migliori">Valutazioni migliori</option>
                            <option value="Valutazioni peggiori">Valutazioni peggiori</option>
                        </select><br><br>
                        <button class="btn" name="ordina" id="ordina"><b>Ordina</b></button>
                        <br>
                    </form>
                </div>
            </div>


            <script>
                function myFunctionOrdinamento() {
                    document.getElementById("myDropdownOrdina").classList.toggle("show");
                }

                window.onclick = function (event) {
                    if (!event.target.matches('.dropbtn')) {
                        var dropdowns = document.getElementsByClassName("dropdown-content");
                        var i;
                        for (i = 0; i < dropdowns.length; i++) {
                            var openDropdown = dropdowns[i];
                            if (openDropdown.classList.contains('show')) {
                                openDropdown.classList.remove('show');
                            }
                        }
                    }
                }
            </script>

            <div class="dropdown">
                <button onclick="myFunctionFiltraggio()" class="dropbtn"><b>Applica filtri:</b></button>
                <div id="myDropdownFiltra" class="dropdown-content">
                    <form action="ValutazioniUtente.jsp" method="post">

                        <select name="cbCategoria" id="cbCategoria"
                                style="width:200px; font-size: 12px; border-color: #277c73;">
                            <option value="" disabled selected>Categoria:</option>
                            <option value="Tutte le categorie">Tutte le categorie</option>
                            <option value="Generica">Generica</option>
                            <option value="Pulizia">Pulizia</option>
                            <option value="Educazione">Educazione</option>
                            <option value="Disponibilità">Disponibilità</option>
                        </select><br>

                        <select name="cbStelle" id="cbStelle"
                                style="width:200px; font-size: 12px; border-color: #277c73;">
                            <option value="" disabled selected>Stelle:</option>
                            <option value="Tutte">Tutte</option>
                            <option value="5 stelle">5 stelle</option>
                            <option value="4 stelle">4 stelle</option>
                            <option value="3 stelle">3 stelle</option>
                            <option value="2 stelle">2 stelle</option>
                            <option value="1 stella">1 stella</option>
                        </select><br>

                        <select name="cbLingua" id="cbLingua"
                                style="width:200px; font-size: 12px; border-color: #277c73;">
                            <option value="" disabled selected>Lingua:</option>
                            <option value="Tutte">Tutte</option>
                            <option value="ita">Italiano</option>
                            <option value="en">Inglese</option>
                            <option value="es">Spagnolo</option>
                            <option value="fr">Francese</option>
                            <option value="de">Tedesco</option>
                        </select><br>

                        <select name="cbRange" id="cbRange"
                                style="width:200px; font-size: 12px; border-color: #277c73;">
                            <option value="" disabled selected>Range data:</option>
                            <option value="Tutte">Tutte</option>
                            <option value="Mar-Mag">Mar-Mag</option>
                            <option value="Giu-Ago">Giu-Ago</option>
                            <option value="Sett-Nov">Sett-Nov</option>
                            <option value="Dic-Feb">Dic-Feb</option>
                        </select><br><br>

                        <button class="btn" name="filtra" id="filtra"><b>Filtra</b></button>
                        <br>

                    </form>
                </div>
            </div>

            <script>
                function myFunctionFiltraggio() {
                    document.getElementById("myDropdownFiltra").classList.toggle("show");
                }

                window.onclick = function (event) {
                    if (!event.target.matches('.dropbtn')) {
                        var dropdowns = document.getElementsByClassName("dropdown-content");
                        var i;
                        for (i = 0; i < dropdowns.length; i++) {
                            var openDropdown = dropdowns[i];
                            if (openDropdown.classList.contains('show')) {
                                openDropdown.classList.remove('show');
                            }
                        }
                    }
                }
            </script>

            <form action="ValutazioniUtente.jsp" method="post">
                <%
                    ArrayList<ValutazioniUtente> allEvaluation = new ArrayList<>();
                    allEvaluation = valutazioniUtenteBean.getEvaluation();
                %>
                <label style="margin-left: 450px; margin-top: 12px;"> Valutazioni trovate: <%=uEs.size()%>
                    / <%=allEvaluation.size()%>
                </label>
            </form>
        </div>

        <form action="ValutazioniUtente.jsp" method="post">
            <ul id="listaValutazioni">
                <%
                    for (int i = 0; i < uEs.size(); i++) {
                %>
                <li id="<%=i%>">
                    <div class="row">
                        <div class="col-sm-3" style="padding-top: 25px">
                            &nbsp;<img src="Img/user.png" alt="user" width="30" height="30"><br>
                            <label id="utente">
                                <font size="4"><b><%=uEs.get(i).getUtente()%>
                                </b></font>
                            </label>
                            <br>
                            <label id="data">
                                <%=uEs.get(i).getData()%>
                            </label>
                        </div>
                        <div class="col-sm-9" style="padding-top: 10px">
                            <label>
                                <%=uEs.get(i).getDescrizione()%>
                            </label>
                            <br>
                            <label id="generic">
                                <font color="#277c73" size="4"><b><%=uEs.get(i).getVal_generica()%>
                                </b></font> /
                                <small> 5</small>
                                <br>
                                Pulizia: <font color="#277c73" size="4"><b><%=uEs.get(i).getVal_pulizia()%>
                            </b></font> /
                                <small> 5</small>
                                <br>
                                Educazione: <font color="#277c73" size="4"><b><%=uEs.get(i).getVal_educazione()%>
                            </b></font> /
                                <small> 5</small>
                                <br>
                                Disponibilità: <font color="#277c73" size="4"><b><%=uEs.get(i).getVal_disponibilita()%>
                            </b></font> /
                                <small> 5</small>
                            </label>
                        </div>
                    </div>
                </li>
                <%
                    }
                %>
            </ul>
        </form>
    </nav>

</section>
</body>
</html>
