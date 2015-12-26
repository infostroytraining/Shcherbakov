$(document).ready(function() {
    $.validator.addMethod("email", function(value, element) {
        return this.optional(element) || /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
    }, "Email Address is invalid: Please enter a valid email address.");

    $.validator.addMethod("password",function(value,element){
        return this.optional(element) || /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$/i.test(value);
    },"Passwords are 8-16 characters with uppercase letters, lowercase letters and at least one number.");

    $("#registration_form").validate({
        rules: {
            email: "required email",
            password: "required password"

        }
    });
    function clearValidation(){
        $('span.error').remove();
    }

    $(document).on( "click", ".register",
        function() {
            $.ajax({
                type : "POST",
                url : "registration",
                data : $("#registration_form").serialize(),
                error : function(data) {
                    console.log(data);
                    clearValidation();
                    $.each(data.responseJSON, function(key, value) {
                        console.log(key);
                        console.log(value);
                        $('#form-' + key).addClass("has-error");
                    });
                }
            });
        });

});