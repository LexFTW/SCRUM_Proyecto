<h1>SCRUM_Proyecto</h1>

<h2>Sprint 1</h2>
<ol>
  <li>Diseñar una Base de Datos para gestionar: Usuarios, grupos de usuarios, proyectos, especificaciones, sprints. (5 horas)</li>
  <li>Hay que utilizar JPA/Hibernate para el acceso a la base de datos remota. (3 horas)</li>
  <li>Los usuarios pueden ser de tipo: <i>Developer</i> (solo podrán visualizar los datos), <i>Product Owner</i> (puede visualizar los datos y añadir/modificar/eliminar especificaciones), <i>Scrum Master</i> (puede visualizar los datos, crear proyectos, crear sprints, temporizar especificaciones y sprints y asignar especificaciones a sprints) y Administrador de usuarios (puede crear usuarios). (1 hora)</li>
  <li>Cuando se inicie la aplicación ha de intentar de conectar a la Base de Datos remota e indicará la conexión con (ONLINE). (1 hora)</li>
  <li>Todas las ventanas internas (JInternalFrames) se podrán cerrar y redimensionar. (2 horas)</li>
  <li>Crear un JInternalFrames de Login. Aparece al ejecutar la aplicación. (1 hora)</li>
  <li>Al pulsar ENTER ha de intentar validar el usuario. (1 hora)</li>
  <li>Ha de tener un JInternalFrame para inscribir usuarios al programa. (4 horas)</li>  
</ol>

<h3>Indicaciones para utilizar la versión 1.0</h3>
<p>
  Para poder tratar la información, en este caso de usuarios, primero debes de importar en tu BD MySQL el archivo que se encuentra dentro de <b><i>main/resources/bd_scrum_arr.sql</i></b> para hacer un volcado de información.
</p>

<p>
  Al iniciar el programa, y que se ejecute correctamente, se ha de inicializar desde la clase Initialitze.class, dentro de <b><i>src/</i></b>
</p>

<p>
  <b>IMPORTANTE</b> para que funcione correctamente la conexión a la BD Remota, comprueba dentro del archivo <i>persistence.xml</i>, dentro de <b></i>main/resources/META-INF</i></b> que en la línea 8, o en la etiqueta < property name="javax.persistence.jdbc.url" value="..." >, la información sea correcta.
</p>

<hr />

<p>
  Además dispones de los siguientes usuarios para poder utilizar en esta versión, aquí una lista de los usuarios:
  <ul>
    <li><b>Usuario:</b> AMengual</li>
    <li><b>Contraseña:</b> Contraseña</li>
    <li><b>Tipo de Usuario:</b> Administrador</li>
    <br />
    <li><b>Usuario:</b> RCarballo</li>
    <li><b>Contraseña:</b> Contraseña</li>
    <li><b>Tipo de Usuario:</b> Scrum Master</li>
    <br />
    <li><b>Usuario:</b> ASalas</li>
    <li><b>Contraseña:</b> Contraseña</li>
    <li><b>Tipo de Usuario:</b> Product Owner</li>
    <br />
    <li><b>Usuario:</b> LZabala</li>
    <li><b>Contraseña:</b> Contraseña</li>
    <li><b>Tipo de Usuario:</b> Developer</li>
  </ul>
  <i>Sí quieres añadir otro usuario, es importante que a la hora de añadir la contraseña lo hagas utilizando SHA1, ya que todas las contraseñas están encriptadas y el programa hace hashing de la contraseña que indicas dentro del JPasswordField</i>
</p>

<h2>Participantes</h2>
<ul>
  <li>Roger Carballo</li>  
  <li>Alexis Mengual</li>
  <li>Adrián Salas</li>
</ul>
