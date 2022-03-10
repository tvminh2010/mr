	var tableToExcel = (function() {
    var uri = 'data:application/vnd.ms-excel;base64,',
    //data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet   //xlsx
      template = '<html ' 
      +'xmlns="urn:schemas-microsoft-com:office:spreadsheet" '
      +'xmlns:o="urn:schemas-microsoft-com:office:office" '
      +'xmlns:x="urn:schemas-microsoft-com:office:excel" '
      +'xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet" '
      +'xmlns:html="http://www.w3.org/TR/REC-html40">'
      +'<meta charset=UTF-8">'
      +'<head><!--[if gte mso 9]>'
      +'<xml><x:ExcelWorkbook>'
      +'<x:ExcelWorksheets>'
      +'<x:ExcelWorksheet>'
      +'<x:Name>{worksheet}</x:Name>'
      +'<x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions>'
      +'</x:ExcelWorksheet>'
      +'</x:ExcelWorksheets>'
      +'</x:ExcelWorkbook></xml>'
      +'<![endif]--></head>'
      +'<body><table border="1">{table}</table></body></html>',
      base64 = function(s) {
        return window.btoa(unescape(encodeURIComponent(s)))
      },
      format = function(s, c) {
        return s.replace(/{(\w+)}/g, function(m, p) {
          return c[p];
        })
      }
    return function(table, name) {
      if (!table.nodeType) table = document.getElementById(table)
      var ctx = {
        worksheet: name || 'Worksheet',
        table: table.innerHTML
      }
      window.location.href = uri + base64(format(template, ctx))
    }
  })();
	
	//Call function :
	//tableToExcel('table_id', 'sheet name')