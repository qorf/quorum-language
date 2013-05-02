<div id="modal-login" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-label-registration" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="modal-registration-header">Sign In to Quorum</h3>
  </div>
  <div class="modal-body">
    <?php 
      print "<h4>Sign in with your credentials or...";
      print '<form action="' . getFullUrl("assets/apis/lightopenid/google.php"). '" method="post">';
      print '<input type="hidden" name="referer" value="' . getRoot() . getPage() . '" />'; 
      print '<input type="hidden" name="action" value="login" />'; ?>
      <input type="submit" class="btn btn-success auth-with-google" value="Sign in with Google" /></form>
    </h4>

    <form class="form-horizontal" id="login-form">
      <fieldset>

        <div class="control-group username">
          <label class="control-label" for="login-username">Username</label>
          <div class="controls">
            <input type="text" class="input-xlarge" name="login-username" id="login-username" />
            <p class="text-error">Please enter a valid username</p>
          </div>
        </div>

        <div class="control-group password">
          <label class="control-label" for="login-password">Password</label>
          <div class="controls">
            <input type="password" class="input-xlarge" name="login-password" id="login-password" />
            <p class="text-error">Please enter a password</p>
          </div>
        </div>

      </fieldset>
    </form>

  </div>
  <div class="modal-footer">
    <div class="loading-spinner"></div>
    <div class="forgot-password">
      <button class="btn btn-warning forgot-password-btn">Forgot Password?</button>
      <form class="forgot-password-form" action="/forgot_password.php" method="post">
        <label for="username">Username:</label><input type="text" name="username" id="username">
        <button class="btn btn-warning">Send Email</button>
      </form>
    </div>

    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
    <button class="btn btn-primary registration-submit">Sign In</button>
  </div>
</div>