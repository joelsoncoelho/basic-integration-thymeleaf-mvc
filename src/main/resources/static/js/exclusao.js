$('#exampleModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  
  var recipient = button.data('id') // Extract info from data-* attributes
  var nome = button.data('nome');

  var modal = $(this);
  var form = modal.find('form');
  var action = form.data('url-base');
  if(!action.endsWith('/')){
	  action += '/';
  }
  form.attr('action', action + recipient);
  
  modal.find('.modal-body span').html('Tem certeza que deseja excluir o médico <strong>' + nome + '</strong>?')
});