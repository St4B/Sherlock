package com.quadible.sherlockplugin

object CompareScreenshotsScript {

    const val name: String = "compare.sh"

    fun getCode(): String = """
        # Add a directory in which previous valid screenshots are stored
        previousPath=${'$'}(pwd)"/screenshots"
        mkdir $`$`previousPath
        
        # Add a directory in which we are going to pull the
        pullPath=${'$'}(pwd)"/pulled-screenshots"
        mkdir $`$`pullPath

        # Add a directory in which we are going to pull the
        resultsPath=$`$`pullPath'/screenshot-testing-results'
        mkdir $`$`resultsPath

        echo ${'$'}'\n*** Start comparing screenshots ***'

        # The following text is being printed by ffmpeg when there
        # are no difference between two images. We will use it in
        # order to skip creating image diffs.
        success='n:1 R:1.000000 G:1.000000 B:1.000000 All:1.000000 (inf)'

        # According to FFMpeg we must use the following graph in our
        # filter, in order to compare two images without any output.
        compareGraph='-filter_complex ssim=stats_file=- -f null -'

        #############################################################
        #         Configuration for generating diff image           #
        #############################################################
        colorbalance='colorbalance=rs=1:gs=-1:bs=-1:rm=1:gm=-1:bm=-1:rh=1:gh=-1:bh=-1'
        # Similarity is the threshold to identify image differences and can take values between [0.01 - 1.0]
        # Blend percentage for the differential pixels can take values between [0.0 - 1.0]
        colorkey='colorkey=white:similarity=0.01:blend=1.0'
        # Opacity of the reference image can take values between [0.0 - 1.0]
        overlay='[0]format=rgba,colorchannelmixer=aa=0.1[bg];[bg][diff]overlay=format=rgb'

        # According to FFMpeg we must use the following graph in our
        # filter, in order to generate an image that contains the
        # differences of the compared images.
        generateDiffGraph="-filter_complex ssim=stats_file=-[ssim];[ssim][1]blend=all_mode=phoenix,format=rgba,$`$`colorkey,$`$`colorbalance[diff];$`$`overlay"

        for file in $`$`pullPath'/'*; do
          if [ -f "$`$`file" ]; then

            image1=$`$`previousPath/$`$`{file##*/}
            echo $`$`image1
            image2=$`$`pullPath/$`$`{file##*/}
            echo $`$`image2
            
            if [ ! -f "$`$`image1" ]; then
              echo "$`$`{file##*/}: NEW"
              cp $`$`image2 $`$`resultsPath/$`$`{file##*/}
              continue
            fi

            # Compare the two images
            result=${'$'}(ffmpeg -i $`$`image1 -i $`$`image2 $`$`compareGraph)

            if [ "$`$`result" == "$`$`success" ]; then
              echo "$`$`{file##*/}: SUCCESS"
              continue
            fi

            echo "$`$`{file##*/}: FAIL"

            # Generates an image that contains the differences of the compared screenshots
            ffmpeg -i $`$`image1 -i $`$`image2 $`$`generateDiffGraph -y $`$`{file##*/} >/dev/null

            mv ${'$'}(pwd)'/'$`$`{file##*/} $`$`resultsPath/$`$`{file##*/}
          fi
        done
    """.trimIndent()

}
