(function(){
    this.Notyf = function(){
      //List of notifications currently active
      this.notifications = [];

      var defaults = {
        delay:2000,
        alertIcon:'notyf-alert-icon',
        confirmIcon:'notyf-confirm-icon'
      }

      if (arguments[0] && typeof arguments[0] == "object"){
        this.options = extendDefaults(defaults, arguments[0]);
      }else{
        this.options = defaults;
      }

      //Creates the main notifications container
      var docFrag = document.createDocumentFragment();
      var notyfContainer = document.createElement('div');
      notyfContainer.className = 'notyf-container';
      docFrag.appendChild(notyfContainer);
      document.body.appendChild(docFrag);
      this.container = notyfContainer;

      //Stores which transitionEnd event this browser supports
      this.animationEnd = animationEndSelect();
    }

    //---------- Public methods ---------------
    /**
    * Shows an alert card
    */
    this.Notyf.prototype.alert = function(alertMessage){
      var card = buildNotificationCard.call(this, alertMessage, this.options.alertIcon);
      card.className += ' alert';
      this.container.appendChild(card);
      this.notifications.push(card);
    }

    /**
    * Shows a confirm card
    */
    this.Notyf.prototype.confirm = function(alertMessage){
      var card = buildNotificationCard.call(this, alertMessage, this.options.confirmIcon);
      card.className += ' confirm';
      this.container.appendChild(card);
      this.notifications.push(card);
    }

    //---------- Private methods ---------------

    /**
    * Populates the source object with the value from the same keys found in destination
    */
    function extendDefaults(source, destination){
      for (property in destination){
        //Avoid asigning inherited properties of destination, only asign to source the destination own properties
        if(destination.hasOwnProperty(property)){
          source[property] = destination[property];
        }
      }
      return source;
    }

    /**
    * Creates a generic card with the param message. Returns a document fragment.
    */
    function buildNotificationCard(messageText, iconClass){
      //Card wrapper
      var notification = document.createElement('div');
      notification.className = 'notyf';

      var wrapper = document.createElement('div');
      wrapper.className = 'notyf-wrapper';

      var iconContainer = document.createElement('div');
      iconContainer.className = 'notyf-icon';

      var icon = document.createElement('i');
      icon.className = iconClass;

      var message = document.createElement('div');
      message.className = 'notyf-message';
      message.innerHTML = messageText;

      //Build the card
      iconContainer.appendChild(icon);
      wrapper.appendChild(iconContainer);
      wrapper.appendChild(message);
      notification.appendChild(wrapper);

      var _this = this;
      setTimeout(function(){
          notification.className += " disappear";
          notification.addEventListener(_this.animationEnd, function(event){
            event.target == notification && _this.container.removeChild(notification);
          });
          var index = _this.notifications.indexOf(notification);
          _this.notifications.splice(index,1);
      },_this.options.delay);

      return notification;
    }

    // Determine which animationend event is supported
    function animationEndSelect() {
      var t;
      var el = document.createElement('fake');
      var transitions = {
        'transition':'animationend',
        'OTransition':'oAnimationEnd',
        'MozTransition':'animationend',
        'WebkitTransition':'webkitAnimationEnd'
      }

      for(t in transitions){
          if( el.style[t] !== undefined ){
              return transitions[t];
          }
      }
    }
})();

var currentAlert = 0;
var notyf = new Notyf({delay:3000});
var checkedValue = true;
function checkForm(formid){
    $('#'+formid).find(".needCheck").each(function(){
        var rule = $(this).attr('data-easyform');
        var message = $(this).attr('data-message');
        var name = $(this).attr('title');
        var value = $(this).val();
        switch(rule){
            case "notnull":
                checkNotNull(name,value,message);
                break;
            case "number":
                checkIsNumber(name,value,message);
                break;
            case "notNullAndNumber":
                checkNotNullAndNumber(name,value,message);
                break;
            case "checkbox":
                checkCheckedBox(formid,name,value,message);
                break;
            default:
                checkNotNull(name,value,message);
        }
    });
    return checkedValue;
}
function notyfAlert(message){
    if(currentAlert>10){
        currentAlert = 0;
    }
    notyf.alert(message);
    currentAlert++;
}
function checkNotNull(name,value,message){
    var reg = new RegExp("/S");
    if(!reg.test(value)){
        //输入框为空
        if(message==""){
            message =name +" 不能为空！";
        }
        notyfAlert(message);
        checkedValue = false;
    }else{
        //输入框不为空
        return true;
    }
}
function checkIsNumber(name,value,message){
    var reg = new RegExp("^[0-9]*$");
    if(value!="" && !reg.test(value)){
        //输入框不为数字
        if(message==""){
            message = name +" 需要数字类型！";
        }
        notyfAlert(message);
        checkedValue = false;
    }else{
        //输入框为空或为数字
        return true;
    }
}
function checkNotNullAndNumber(name,value,message){
    if(checkNotNull(name,value,message) && checkIsNumber(name,value,message)){
        return true;
    }else{
        checkedValue = false;
    }
}
function checkCheckedBox(formid,name,value,message){
    var checkBoxs = $('#'+formid).find("input[name='"+name+"']");
    if(checkBoxs.length>0){
        return true;
    }else{
        if(message==""){
            message = name+" 最少选择一个!";
        }
        notyfAlert(message);
        checkedValue = false;
    }
}
