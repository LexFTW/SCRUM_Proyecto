<h1>SCRUM_Proyecto</h1>

<h2>Sprint 1</h2>
<ol>
  <li>
    <s>
      Diseñar una Base de Datos para gestionar: Usuarios, grupos de usuarios, proyectos, especificaciones, sprints. (5 horas)
    </s>
  </li>
  <li>
    <s>
      Hay que utilizar JPA/Hibernate para el acceso a la base de datos remota. (3 horas)
    </s>
  </li>
  <li>
    <s>
      Los usuarios pueden ser de tipo: <i>Developer</i> (solo podrán visualizar los datos), <i>Product Owner</i> (puede visualizar los datos y añadir/modificar/eliminar especificaciones), <i>Scrum Master</i> (puede visualizar los datos, crear proyectos, crear sprints, temporizar especificaciones y sprints y asignar especificaciones a sprints) y Administrador de usuarios (puede crear usuarios). (1 hora)     </s>
  </li>
  <li>
    <s>
      Cuando se inicie la aplicación ha de intentar de conectar a la Base de Datos remota e indicará la conexión con (ONLINE). (1 hora)     </s>
  </li>
  <li>
    <s>Todas las ventanas internas (JInternalFrames) se podrán cerrar y redimensionar. (2 horas)
    </s>
  </li>
  <li>
    <s>
      Crear un JInternalFrames de Login. Aparece al ejecutar la aplicación. (1 hora)
    </s>
  </li>
  <li>
    <s>
      Al pulsar ENTER ha de intentar validar el usuario. (1 hora)
    </s>
  </li>
  <li>
    <s>
      Ha de tener un JInternalFrame para inscribir usuarios al programa. (4 horas)
    </s>
  </li>  
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

<h2>Sprint 2</h2>
<ol>
  <li><s>Generar contraseña automaticamente de 6 caracteres. (1h)</s></li>
  <li><s>Generar Login automaticamente mediante nombre y primer apellido. (2h)</s></li>
  <li><s>Validar contraseña, en caso de error, mostrar un JLabel con un texto rojo. (1h)</s></li>
  <li><s>Validar formato del correo electronico. (1h)</s></li>
  <li><s>(13,15,16) Ventana para generar un proyecto. (3h)</s></li>
  <li><s>Replicar inserts en la BD embebida. (4h)</s></li>
  <li><s>Botón para cerrar sesión. (1h)</s></li>
  <li><s>Que la aplicación funcione a través de la BD embebida. (2h)</s></li>
  <li><s>Hacer JMenuBar y gestionar los permisos de acceso. (1h)</s></li>
  <li><s>Generar archivo log. (2h)</s></li>
</ol>

<h2>Sprint 3</h2>
<ol>
  <li>Generar la vista para visualizar datos de un proyecto - (3 h)</li>
  <li>El botón de Mostrar las especificaciones del proyecto seleccionado (5 h)</li>
  <li>Comprobar si hay acciones OFFLINE y replicarla a la remota - (5 h)</li>
  <li>Botón de añadir especificación - (2 h)</li>
  <li>Crear procedure store para hacer un user</li>
  <li>Errores del Sprint 2.</li>
</ol>

<h2>Participantes</h2>
<ul>
  <li>Roger Carballo</li>  
  <li>Alexis Mengual</li>
  <li>Adrián Salas</li>
</ul>

<h2>Retrospectiva</h2>
  <i>La ruta de la retrospectiva en el proyecto es: Proyecto_SCRUM --> src --> main --> resources --> retrospectiva</i>
