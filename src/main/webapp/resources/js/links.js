$(document).ready(

	function(e) {
		$('.selectpicker').selectpicker(
				{
					style: 'btn-default'
				}
		)
		$("#".concat($('body').attr('page'))).addClass('active');
	}

)