# Ribbon
ribbon animation
demo

```
  mImageView = (ImageView) findViewById(R.id.iamge);
        final RibbonDrawable drawable = new RibbonDrawable(this);
        RibbonConfig config = new RibbonConfig()
                .setColors(new int[]{0XFFD53034, 0XFFFFD83A, 0XFFE073FF, 0XFF3FDBEA})
                .setShapeTypes(new int[]{RibbonShape.CIRCLE, RibbonShape.RECT_LONG,
                        RibbonShape.RECT, RibbonShape.TRIANGLE, RibbonShape.STAR})
                .setVelocity(2)
                .setCreadCount(10)
                .setMinScaleX(1.5f)
                .setMaxScaleX(1.8f)
                .setMinScaleY(1.5f)
                .setMaxScaleY(1.8f)
                .setDuration(5000);
        drawable.setRibbonConfig(config);

        drawable.setAnimationListener(new RibbonDrawable.AnimationListener() {
            @Override
            public void onStart() {
                Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopCreate() {

            }

            @Override
            public void onStop() {
                Toast.makeText(MainActivity.this, "stop", Toast.LENGTH_SHORT).show();
            }
        });
        mImageView.setImageDrawable(drawable);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable.start();
            }
        });
```
