<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="cc" uri="http://bill.dudney.net/cc/component" %>
<html>
  <head>
    <title>Credit Card Component Example</title>
  </head>
  <body>
   <h1>Hello and welcome to the example</h1>
   <f:view>
   <p>
   <h:messages id="messageList" showSummary="true"/>
   </p>
   <h:form>
     <h:outputLabel for="test">
       <h:outputText id="testLabel" value="Credit Card Number:"/>
     </h:outputLabel> 
     <cc:creditCardInput id="test" size="19"
                         value="#{page.ccNum}"/>
     <br/>
     <h:commandButton value="Save" 
              	       action="#{page.saveNumber}"/>
   </h:form>
   </f:view>
  </body>
</html>
