# READ ME FOR THE SPRINT 
# Framwork
S4- Framework Sping 

Sprint 1 :
    Pour avoir la liste des controllers annoter:
        -cree un projet test
        -copier le bin/myLib/framework.jar dans le lib du projet
        -puis dans le fichier src 
            placer les classes controllers dans le package com/controller
        ps:si vous voulez modifier le package, il suffit de changer le xml dans la balise init param
        - annoter les classes comme suis "@Annotation controller"
        - puis deployer sur tomcat

sprint 4
    A modifier dans web.xml projet test:
    - mettre controllers.FrontController comme servlet initial
    - importer annotations.* pour chaque controller
    - mettre tous les controlleurs dans un meme package et les annoter 
    <!-- @AnnotationController -->
    - ajouter en utilisant init-param avec le servlet initial le package contenant les controlleurs et mettre comme param-name 'controller'
        <!-- <init-param>
        <param-name>controller</param-name>
        <param-value>nom_de_votre_package</param-value>
        </init-param> -->
    - annoter vos methodes dans vos controllers de la maniere suivante
        <!-- @GET("votre_nom_de_methode") -->
    - mettre mes fichiers jsp dans:
        webapps/mon_projet/mes_fichiers.jsp
    - pour direger vers un view utiliser ModelView
    et ajouter les donnees a envoyer vers le ficher jsp a l'aide de addData dans la class ModelView
    exemple:
    <!-- @GET("listeEmp")
        public ModelView listerData() {
            ModelView mv = new ModelView("test.jsp");
            String anarana = "Jean";
            int age = 20;
            mv.addData("nom", anarana);
            mv.addData("nbr", age);
            return mv;
        } -->


Sprint 9:
-But: exposer les actions en Rest Api
    1-creeer une nouvelle classe annotation ex:Restapi
    2-dans frontcontroller, verifier l'existence de cette anotation
        si oui,
            -recuperer la valeur de retour de la methode(gson)
                -si modelview, transformer attribut data en json
                -else translate json directement
            -ne plus utiliser dispatch mais utilsier getwriter et ptintjson
            -changer le response type json

Sprint 10:
    Côté dev :

    Permettre aux développeurs de définir explicitement le type de requête HTTP qu'une méthode doit gérer via les annotations @GET et @POST.
    Si aucune annotation n'est définie, la méthode est considérée comme GET par défaut.

    Côté framework :

        Le framework doit vérifier le type de requête HTTP (GET ou POST) avant d'appeler une méthode.
        Si une méthode est appelée avec un mauvais verbe HTTP (par exemple POST au lieu de GET), le framework doit lever une exception ou renvoyer une erreur au client.


Sprint 10 - part two :
    objectif : permettre au framework de gerer un url avec des actions differentes en fonction du verb au quel il est associer
    @url=('add')
    @Get
    method1()

    @url=('add')
    @POST
    method2()


    comment :
        -creer une classe verbaction avec deux attributs 
            string nethodname
            string verb
        - modfifier la classe mapping pour que le method name soit une list de verbaction

        -lors du scan des package controller 
            quand on mappe les methodes prou avoir Hashmap(string url, mapping)
                -set aussi les verbaction 
                si les methodes sont annoter Post.class =>  mapping.getverbaction= post
        
        -modifier front controller pour gerer ca

sprint 11:
    objectif: afficher erreur rhf tsisy lien ita

sprint 12 :
    objectif :permette au develeppeur d'uploader un ou plusieurs fichiers
    comment :
 
        -ajouter une annotaion multipartconfig a mon front controller
        -creer classe Partperso pour stocker fichiers(nom fichier string, byte)
        -creer une classe annotation partAnnotation pour annoter les fichiers dans le form , (value= nameform)
        -dans le controlleur,
            -recuperer le fichier et sauvegarder dans un autre fichier puis l'afficher

    done : 
    
    to- do:
        - amelioration lets the developer choose where to upload it , add conf file and read it 


sprint 13:
    annnotation required or range on the param 
    -throws expetion

----------------------------------------------------------------------------------------------------------
Sprint0:
Git
    -Créer un projet Git
    -Cloner localement
    -Creer une branche pour le Sprint actuel nommé: sprint[n° sprint]-[etu]
    -A la fin du sprint, envoyer un "Merge request" du sprint et supprimer la branche
Sprint0
    Objectif: Créer un servlet qui réceptionnera toutes les requêtes clients et qui les traitera
    Etapes:
        -Coté Framework:
            -Créer un servlet FrontController dont la methode processRequest affichera l'url dans 
            lequel on se trouve
        -Coté Test
            -Associer le FrontController à l'url pattern "/" dans le web.xml du projet
            -Tester n'importe quel url associé au projet web
NB: La partie Test ne sera pas envoyée sur Git
-----------------------------------------------------------------------------------------------------------
Sprint 01:
Creer une nouvelle branche "sprint1-ETU"
Et envoyer le framework sur git.
Modif dans mon framework :
1-Creer AnnotationController
2- Annoter mes controleurs avec AnnotationController
3- Mettre mes controleurs dans le meme package
Modif dans FrontController :
(Prendre le nom du package où se trouvent mes controleurs)
1- Tester si j'ai déjà scanner mes controleurs
+ Si oui, afficher la liste des noms de mes controleurs 
+Sinon scanner, puis afficher la liste des noms de mes controleurs 
Modif dans le projet de test:
Web.xml
     + declarer  le nom du package (misy ny controller rehetra) (using init-param)
     + declarer mon frontServlet
‌Creer un ReadMe file pour décrire précisément les configs à faire pour utiliser mon framework.(envoyer le ReadMe file avec mon framework sur Git)
Enjoy !!!🙃
---------------------------------------------------------------------------------------------------------------
Sprint 02
Objectif :
 Récupérer la classe et la méthode associées à une URL donnée
Étapes :
 # Créer une annotation GET pour annoter les méthodes dans les contrôleurs
 # Créer la classe Mapping qui aura pour attributs :
 String className
 String methodName
 # Dans FrontController :
 - Enlever l'attribut boolean
 - Créer un HashMap (String url, Mapping)
 - init :
 Faire les scans pour avoir les contrôleurs
 Pour chaque contrôleur, prendre toutes les méthodes et voir s'il y a l'annotation GET
 S'il y en a, créer un nouveau Mapping : (controller.name, method.name)
 HashMap.associer(annotation.value, Mapping)
 # ProcessRequest
 Prendre le Mapping associé au chemin URL de la requête
 Si on trouve le Mapping associé, afficher le chemin URL et le Mapping
 Sinon, afficher qu'il n'y a pas de méthode associée à ce chemin
---------------------------------------------------------------------------------------------------------------------
Salama 🙃, - SPRINT 03 -
Objectif :
Exécuter la méthode de la classe associée à une URL donnée
Étapes :
# Dans le FrontController ( ProcessRequest ):
Si on trouve le Mapping associé à l'URL ,
- Récupérer la classe par son nom
- Récupérer la méthode par son nom
- Invoquer la méthode sur l'instance de la classe
- Afficher la valeur retournée par la méhode
# Projet Test
Les méhodes des controlleurs qui seront annotées ont pour type de retour "String"
-------------------------------------------------------------------------------------------------------------------
Salama daholo
#sprint_4
Objectif:
Envoyer des données du controller vers view
Etapes:
Côté Framework
_créer une classe ModelView qui aura pour attributs: 
 _String url[url de destination après l'exécution de la méthode], 
 _HashMap<String : nom de la variable, Object: sa valeur)> data [donnée à envoyer vers cette view],
    _créer une fonction "AddObject" qui a comme type de retour void pour pouvoir mettre les données dans HashMap
Dans FrontController,
 dans ProcessRequest, récupérer les données issues de la méthode annotée Get
     _si les data sont de type string, retourner la valeur directement
     _si les données sont de type ModelView, récupérer le url et dispatcher les données vers cet url 
  _boucle de data: y faire request.setAttribute
     _si autre, retourner "non reconnu"
Test: 
Les méthodes des controlleurs qui seront annotées ont pour type de retour "String" ou "ModelView"
-------------------------------------------------------------------------------------------------------------
Hello daholo
#sprint6
SPRINT 6 : Envoyer des données d'un formulaire vers un contrôleur
FRAMEWORK :
Étape 1 : Créer une annotation @Param
Attribut :
-   String name
Étape 2 : Ajouter un argument HttpServletRequest request dans la fonction invoquant les méthodes des contrôleurs
-   Boucler sur les arguments de la méthode du contrôleur et récupérer les noms des annotations @Param de chaque argument
-   Attribuer la valeur de chaque argument en utilisant request.getParameter avec le nom de son annotation comme argument
TEST :
-   Créer un formulaire d'envoi (ex : Entrer votre nom)
-   Créer une méthode dans un contrôleur pour récupérer le nom entré
-   Renvoyer un ModelView pour vérifier si le nom est bien récupéré
NOTE :
Pour les liens GET tels que "emplist?ville=105"
-   Lors de la récupération du Mapping, enlever le texte après "?" et utiliser le lien avant "?"
------------------------------------------------------------------------------------------------------------
Bonjour tous le monde,
#sprint07
Objectif:
Permettre de mettre en paramètre d'une fonction de mapping un objet et de setup ses attributs.

Etape 1: Créer une annotation pour l'objet en paramètre
Etape 2: Créer un process qui va s'effectuer automatiquement lors que le programme détecte l'annotation créée plus tôt
-> Ce process va bouclé tous les attributs de l'objet pour obtenir leurs valeurs attribuées dans request.getParameter
-> Créer une nouvelle annotation de type ElementType.FIELD pour donner le choix aux utilisateurs du framework le choix entre utilisé le même nom dans sa classe et son formulaire ou annoté l'attribut avec le nom présent dans son formulaire sans devoir à utilisé le même nom

A part cela, ce sera le même process que le #sprint6.

Remarque:
Dans le #sprint6, il nous a été demandé que si jamais l'utilisateur du framework n'avais pas annoté ses paramètres d'utilisé le nom des paramètres en question.
Problème: Reflect API, pour des raisons de sécurités, ne renvoi que des noms génériques si nous ne spécifions pas -parameters lors de la compilation du projet.
Pour pallier à cela, nous allons utilisés une librairie externe paranamer de Throughwork: https://mvnrepository.com/.../com.../paranamer/2.8
L'image ci-jointe donne un exemple de code
--------------------------------------------------------------------------------------------------------------------
Bonjour tout le monde,
#sprint07
Rectification: Nous pouvons maintenant utilisé -parameters lors de la compilation
-------------------------------------------------------------------------------------------------------------------
Salama daholo
#sprint8
Sprint 8 : Gestion et utilisation de session
Côté Framework :
Étape 1 :
- Créer une classe MySession ayant comme seul attribut HttpSession session
- Ajouter fonctions get(String key), add(String key, Object objet), delete(String key)
Étape 2 :
- A l’appel des méthodes des controllers de l’utilisateur, pendant la génération des arguments, vérifier si le paramètre est de type MySession et dans ce cas, créer un MySession avec req.getSession()
Côté test :
- Créer un formulaire de login (identifiant,mot de passe)
- Quand la personne se connecte, elle accède à une liste de données propres à son identifiant
-Ajouter un bouton déconnexion qui supprime les données de la session
Vous pouvez utiliser n’importe quel type pour les listes de données mais sans utiliser de système de base de donnée
-------------------------------------------------------------------------------------------------------------------
MR NAINA
Miarahaba,
ny sujet an'ny sprint 9 dia exposition na méthode d'action anaty controleur ho lasa json no averiny fa tsy mande @vue (rest api)
Samia manao reflexion dia alefako eto ny zoma 20 sept @14h ny torolalana hanaovana azy
Misaotra
--------------------------------------------------------------------------------------------------------------------
MR NAINA
Miarahaba
@sprint 10 :
hatreto isika dia mbola ny method(verb) GET iany no mbola supporté
ny tanjona zany dia afaka hi supporter ny methode POST
Samia manao reflexion aloha dia mandefa approche eo aho @alatsinainy
Ny zoma 27sept dia hanomboka ilay clustering isika
Hisy vidéo mifanaraka @zay hilatsaka eo 🙂
Mazotoa
-----------------------------------------------------------------------------------------------------------------
MR NAINA
Miarahaba
Approche hitondrana ny s10 :
ny coté dev aloha znay mila afaka mametraka annotation eo @methode : @GET sy @POST
raha tsy misy ny 1 @ireo dia GET par défaut (ohatrany ataotsika zao)
Coté framework indray
mila fantatra zany hoe. GET sa POST ilay methode dia miankina @iny ko le Url
teo aloha izany dia ny url dia associé @Classe + action (methode any @controlleur) fa @zao mila ampiana attirbut hafa (VERB) dia io zany @zao soit GET soit POST
raha misy cas oahtr zao zany dia tokony mi lever exception : methode na controlleur : @GET @url("/getemp") getEmp .... d nefa ilay ilay url "getemp" io antsoina @verb (method) POST !!
Apetraho eo ambany ny fanontaniana raha misy tsy azo
(aza handefasana mp na whatapp aho azafady)
Misaotra
------------------------------------------------------------------------------------------------------------------
MR NAINA
Miarahaba,
Manohy ilay sprint10 hianareo androany
izay tsy mbola nahavita sprint9 mo dia manenjika ihany koa
@zoma manome ny objectif an'ny sprint11
Amin'ny herinandro ho avy vao tohizantsika ny clustering
Afaka mametraka fanontaniana eo fa valiako
Mr Glenn manaraka anareo eo ihany
Misaotra
-------------------------------------------------------------------------------------------------------------------