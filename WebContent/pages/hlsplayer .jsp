<html>
<head>
    <script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/hls.js@latest"></script>
  <script src="{% static 'js/videoplayer/mousetrap.min.js' %}" charset="utf-8"></script>
  <script src="<spring:url value='resources/js/mousetrap.min.js'/>" charset="utf-8"></script>
          <script src="<spring:url value='resources/js/player.js'/>"></script>
  <style>
    body{
      background-color:black;
    }
    #video{
      position: absolute;
      top: 0px;
      right: 0px;
      bottom: 0px;
      left: 0px;
      margin: auto;
      max-height: 100%;
      max-width: 100%;
    }
  </style>
<title>M3U8 Player running...</title></head>
<body>
  <script>
    var url="https://d2zihajmogu5jn.cloudfront.net/bipbop-advanced/bipbop_16x9_variant.m3u8"
  </script>
  <video id="video" style="width: 100%; height: 100%;" controls=""></video>
  <script src="{% static 'js/videoplayer/player.js' %}"></script>
</body>
</html>
